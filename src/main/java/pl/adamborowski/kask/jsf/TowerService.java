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
    private SortedMap<Integer, Sorcerer> wizzards;


    public TowerService() {
        towers = new TreeMap<>();
        wizzards = new TreeMap<>();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
            Unmarshaller u = jaxbContext.createUnmarshaller();
            Root root = (Root) u.unmarshal(getClass().getResourceAsStream("/pl/adamborowski/kask/jsf/xml/towers.xml"));
            for (Tower tower : root.getTowers()) {
                towers.put(tower.getId(), tower);
                if (tower.getWizzards() != null) {
                    for (Sorcerer wizzard : tower.getWizzards()) {
                        wizzards.put(wizzard.getId(), wizzard);
                        wizzard.setTower(tower);
                    }
                }
            }
        } catch (JAXBException ex) {
            log.log(Level.WARNING, ex.getMessage(), ex);
        }
    }

    private List<Tower> asList(Tower... towers) {
        return findAllTowers();
    }

    public List<Tower> findAllTowers() {
        return new ArrayList<>(towers.values());
    }

    public List<Sorcerer> findAllWizzards() {
        return new ArrayList<>(wizzards.values());
    }

    public Tower findTower(int id) {
        return towers.get(id);
    }

    public Sorcerer findWizzard(int id) {
        return wizzards.get(id);
    }

    public void removeTower(Tower tower) {
        towers.remove(tower.getId());
    }

    public void removeWizzard(Sorcerer wizzard) {
        wizzard.getTower().getWizzards().remove(wizzard);
        wizzards.remove(wizzard.getId());
    }

    public void saveTower(Tower tower) {
        if (tower.getId() == 0) {
            tower.setId(towers.isEmpty() ? 1 : towers.lastKey() + 1);
        }
        towers.put(tower.getId(), tower);
    }

    public void saveWizzard(Sorcerer wizzard) {
        if (wizzard.getId() == 0) {
            //dodanie
            wizzard.setId(wizzards.isEmpty() ? 1 : wizzards.lastKey() + 1);
        }
        wizzards.put(wizzard.getId(), wizzard);
        for (Tower tower : towers.values()) {
            tower.getWizzards().remove(wizzard);
        }
        wizzard.getTower().getWizzards().add(wizzard);

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
