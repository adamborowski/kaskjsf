package pl.adamborowski.kask.jsf;

import lombok.extern.java.Log;
import pl.adamborowski.kask.jsf.entities2.*;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;

/**
 * Created by adam on 15.10.14.
 */
@ManagedBean
@ViewScoped
@Log
public class TowerService {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;



    private List<Tower> asList(Tower... towers) {
        return findAllTowers();
    }

    public List<Tower> findAllTowers() {
        return em.createNamedQuery("Tower.findAll").getResultList();
    }

    public List<Sorcerer> findAllWizzards() {
        return em.createNamedQuery("Sorcerer.findAll").getResultList();
    }

    public Tower findTower(int id) {
        return em.find(Tower.class, id);
    }

    public Sorcerer findWizzard(int id) {
        return em.find(Sorcerer.class, id);
    }

    public void removeTower(Tower tower) {
        try {
            utx.begin();
            //zamień zwyły bean (przychodzi cokolwiek skądkolwiek) na bean z EM
            tower= em.merge(tower);
            em.remove(tower);
            //cascade onetomany więc wizzardy też pójdą się golić
            utx.commit();
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            try {
                utx.rollback();
            } catch (Exception ex1) {
                log.log(Level.SEVERE, null, ex1);
            }
        }

    }

    public void removeWizzard(Sorcerer wizzard) {
        try {
            utx.begin();
            //zamień zwyły bean (przychodzi cokolwiek skądkolwiek) na bean z EM
            wizzard= em.merge(wizzard);
            em.remove(wizzard);
            utx.commit();
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            try {
                utx.rollback();
            } catch (Exception ex1) {
                log.log(Level.SEVERE, null, ex1);
            }
        }
    }

    public void saveTower(Tower tower) {
        try {
            utx.begin();
            if (tower.getId() == null) {
                em.persist(tower);
            } else {
                em.merge(tower);
            }
            utx.commit();
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            try {
                utx.rollback();
            } catch (Exception ex1) {
                log.log(Level.SEVERE, null, ex1);
            }
        }
    }

    public void saveWizzard(Sorcerer wizzard) {
        try {
            utx.begin();
            if (wizzard.getId() == null) {
                em.persist(wizzard);
            } else {
                em.merge(wizzard);
            }
            utx.commit();
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            try {
                utx.rollback();
            } catch (Exception ex1) {
                log.log(Level.SEVERE, null, ex1);
            }
        }

    }


    public void marshalLibrary(OutputStream out) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
            Marshaller m = jaxbContext.createMarshaller();
            Root library = new Root();
            library.getTowers().addAll(findAllTowers());
            m.marshal(library, out);
        } catch (JAXBException ex) {
            log.log(Level.WARNING, ex.getMessage(), ex);
        }
    }
}
