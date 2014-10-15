package pl.adamborowski.kask.jsf.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.adamborowski.kask.jsf.TowerService;
import pl.adamborowski.kask.jsf.entities2.Tower;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * @author psysiu
 */
@ViewScoped
@ManagedBean
@NoArgsConstructor
public class ListTowers implements Serializable {

    public void setTowerService(TowerService towerService) {
        this.towerService = towerService;
    }

    @ManagedProperty("#{towerService}")
    private TowerService towerService;


    private List<Tower> towers;

    public List<Tower> getTowers() {
        if (towers == null) {
            towers = towerService.findAllTowers();
        }
        return towers;
    }

    public void removeTower(Tower tower) {
        towerService.removeTower(tower);
        towers.remove(tower);
    }

    public void downloadLibraryXML() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
        ec.setResponseContentType("text/xml"); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"library.xml\""); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.

        OutputStream output = ec.getResponseOutputStream();

        towerService.marshalLibrary(output);

        fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
    }
}
