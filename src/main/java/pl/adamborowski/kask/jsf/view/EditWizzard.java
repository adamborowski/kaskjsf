package pl.adamborowski.kask.jsf.view;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.adamborowski.kask.jsf.TowerService;
import pl.adamborowski.kask.jsf.entities2.Environment;
import pl.adamborowski.kask.jsf.entities2.Sorcerer;
import pl.adamborowski.kask.jsf.entities2.Tower;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * @author psysiu
 */
@ViewScoped
@ManagedBean
@Log
public class EditWizzard implements Serializable {

    @ManagedProperty("#{towerService}")
    private TowerService towerService;

    @Getter
    @Setter
    private int wizzardId;


    @Getter
    @Setter
    private Sorcerer wizzard;


    public void setTowerService(TowerService towerService) {
        this.towerService = towerService;
    }

    public void init() {
        if (wizzard == null && wizzardId != 0) {
            wizzard = towerService.findWizzard(wizzardId);
        } else if (wizzard == null && wizzardId == 0) {
            wizzard = new Sorcerer();
        }
        if (wizzard == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error/404.xhtml");
            } catch (IOException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
        //todo zapisać poprzedni tower??
    }

    private List<SelectItem> environmentsAsSelectItems;

    public List<SelectItem> getEnvironmentsAsSelectItems() {
        if (environmentsAsSelectItems == null) {
            environmentsAsSelectItems = new ArrayList<>();
            for (Environment environment : Environment.values()) {
                environmentsAsSelectItems.add(new SelectItem(environment, environment.toString()));
            }
        }
        return environmentsAsSelectItems;
    }

    private List<SelectItem> towersAsSelectItems;

    public List<SelectItem> getTowersAsSelectItems() {
        if (towersAsSelectItems == null) {
            towersAsSelectItems = new ArrayList<>();
            for (Tower tower : towerService.findAllTowers()) {
                towersAsSelectItems.add(new SelectItem(tower, tower.getName()));
            }
        }
        return towersAsSelectItems;
    }

    public String saveWizzard() {
        towerService.saveWizzard(wizzard);
        return "view_tower?faces-redirect=true&towerId=" + wizzard.getTower().getId();
    }
}
