package pl.adamborowski.kask.jsf;

import lombok.extern.java.Log;
import pl.adamborowski.kask.jsf.entities2.*;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
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
@ApplicationScoped
@Log
public class TowerService {
    private SortedMap<Integer, Tower> towers;


    public TowerService() {
        towers = new TreeMap<>();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
            Unmarshaller u = jaxbContext.createUnmarshaller();
            Root root = (Root) u.unmarshal(getClass().getResourceAsStream("/pl/adamborowski/kask/jsf/xml/towers.xml"));
            for (Tower tower : root.getTowers()) {
                towers.put(tower.getId(), tower);
//                for (Sorcerer author : book.getWizzard()) {
//                    towers.put(author.getId(), author);
//                }
            }
        } catch (JAXBException ex) {
            log.log(Level.WARNING, ex.getMessage(), ex);
        }
    }

    private List<Tower> asList(Tower... authors) {
        return findAllTowers();
    }

    public List<Tower> findAllTowers() {
        return new ArrayList<>(towers.values());
    }

    public Tower findTower(int id) {
        return towers.get(id);
    }

    public void removeTower(Tower tower) {
        towers.remove(tower.getId());
    }

    public void saveTower(Tower tower) {
        if (tower.getId() == 0) {
            tower.setId(towers.lastKey() + 1);
        }
        towers.put(tower.getId(), tower);
    }


    public void marshalLibrary(OutputStream out) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
            Marshaller m = jaxbContext.createMarshaller();
            Root library = new Root();
            library.getTowers().addAll(towers.values());
            m.marshal(library, out);
        } catch (JAXBException ex) {
            log.log(Level.WARNING, ex.getMessage(), ex);
        }
    }
}
