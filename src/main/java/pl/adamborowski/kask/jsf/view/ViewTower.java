package pl.adamborowski.kask.jsf.view;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.adamborowski.kask.jsf.BookService;
import pl.adamborowski.kask.jsf.TowerService;
import pl.adamborowski.kask.jsf.entities.Book;
import pl.adamborowski.kask.jsf.entities2.Tower;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;

/**
 *
 * @author psysiu
 */
@ViewScoped
@ManagedBean
@Log
public class ViewTower implements Serializable {

    @ManagedProperty("#{towerService}")
    private TowerService towerService;

    @Getter
    @Setter
    private int towerId;

    @Getter
    @Setter
    private Tower tower;

    public void setTowerService(TowerService towerService) {
        this.towerService = towerService;
    }
    
    public void init() {
        if (tower== null) {
            tower= towerService.findTower(towerId);
        }
        if (tower == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error/404.xhtml");
            } catch (IOException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
    }
}
