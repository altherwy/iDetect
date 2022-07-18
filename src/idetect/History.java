/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package idetect;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author youssef 19
 */
@Entity
@Table(name = "history", catalog = "idetect", schema = "")
@NamedQueries({
    @NamedQuery(name = "History.findAll", query = "SELECT h FROM History h"),
    @NamedQuery(name = "History.findByUrl", query = "SELECT h FROM History h WHERE h.url = :url"),
    @NamedQuery(name = "History.findByActive", query = "SELECT h FROM History h WHERE h.active = :active"),
    @NamedQuery(name = "History.findByIsPhished", query = "SELECT h FROM History h WHERE h.isPhished = :isPhished"),
    @NamedQuery(name = "History.findByWebsiteIDforeign", query = "SELECT h FROM History h WHERE h.websiteIDforeign = :websiteIDforeign"),
    @NamedQuery(name = "History.findByHistoryID", query = "SELECT h FROM History h WHERE h.historyID = :historyID"),
    @NamedQuery(name = "History.findByTimeDateForeign", query = "SELECT h FROM History h WHERE h.timeDateForeign = :timeDateForeign")})
public class History implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Column(name = "URL")
    private String url;
    @Column(name = "active")
    private String active;
    @Column(name = "isPhished")
    private String isPhished;
    @Column(name = "websiteID_foreign")
    private Integer websiteIDforeign;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "historyID")
    private Integer historyID;
    @Column(name = "time_date_foreign")
    private Integer timeDateForeign;

    public History() {
    }

    public History(Integer historyID) {
        this.historyID = historyID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        String oldUrl = this.url;
        this.url = url;
        changeSupport.firePropertyChange("url", oldUrl, url);
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        String oldActive = this.active;
        this.active = active;
        changeSupport.firePropertyChange("active", oldActive, active);
    }

    public String getIsPhished() {
        return isPhished;
    }

    public void setIsPhished(String isPhished) {
        String oldIsPhished = this.isPhished;
        this.isPhished = isPhished;
        changeSupport.firePropertyChange("isPhished", oldIsPhished, isPhished);
    }

    public Integer getWebsiteIDforeign() {
        return websiteIDforeign;
    }

    public void setWebsiteIDforeign(Integer websiteIDforeign) {
        Integer oldWebsiteIDforeign = this.websiteIDforeign;
        this.websiteIDforeign = websiteIDforeign;
        changeSupport.firePropertyChange("websiteIDforeign", oldWebsiteIDforeign, websiteIDforeign);
    }

    public Integer getHistoryID() {
        return historyID;
    }

    public void setHistoryID(Integer historyID) {
        Integer oldHistoryID = this.historyID;
        this.historyID = historyID;
        changeSupport.firePropertyChange("historyID", oldHistoryID, historyID);
    }

    public Integer getTimeDateForeign() {
        return timeDateForeign;
    }

    public void setTimeDateForeign(Integer timeDateForeign) {
        Integer oldTimeDateForeign = this.timeDateForeign;
        this.timeDateForeign = timeDateForeign;
        changeSupport.firePropertyChange("timeDateForeign", oldTimeDateForeign, timeDateForeign);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historyID != null ? historyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof History)) {
            return false;
        }
        History other = (History) object;
        if ((this.historyID == null && other.historyID != null) || (this.historyID != null && !this.historyID.equals(other.historyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idetect.History[historyID=" + historyID + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
