package domini;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/*
 * @author Laia Sanahuja
 */
@Entity
@Table(name = "RESERVES")
@NamedQueries({
    @NamedQuery(name = "Reserves.findAll", query = "SELECT r FROM Reserves r")
    , @NamedQuery(name = "Reserves.findByNif", query = "SELECT r FROM Reserves r WHERE r.reservesPK.nif = :nif")
    , @NamedQuery(name = "Reserves.findByCodiviatge", query = "SELECT r FROM Reserves r WHERE r.reservesPK.codiviatge = :codiviatge")
    , @NamedQuery(name = "Reserves.findByPlacesreservades", query = "SELECT r FROM Reserves r WHERE r.placesreservades = :placesreservades")})
public class Reserves implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReservesPK reservesPK;
    @Column(name = "PLACESRESERVADES")
    private Short placesreservades;
    @JoinColumn(name = "NIF", referencedColumnName = "NIF", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuaris usuaris;
    @JoinColumn(name = "CODIVIATGE", referencedColumnName = "CODI", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Viatges viatges;

    public Reserves() {
    }

    public Reserves(ReservesPK reservesPK) {
        this.reservesPK = reservesPK;
    }

    public Reserves(String nif, int codiviatge, short placesreservades) {
        this.reservesPK = new ReservesPK(nif, codiviatge);
        this.placesreservades = placesreservades;
    }

    public ReservesPK getReservesPK() {
        return reservesPK;
    }

    public void setReservesPK(ReservesPK reservesPK) {
        this.reservesPK = reservesPK;
    }

    public Short getPlacesreservades() {
        return placesreservades;
    }

    public void setPlacesreservades(Short placesreservades) {
        this.placesreservades = placesreservades;
    }

    public Usuaris getUsuaris() {
        return usuaris;
    }

    public void setUsuaris(Usuaris usuaris) {
        this.usuaris = usuaris;
    }

    public Viatges getViatges() {
        return viatges;
    }

    public void setViatges(Viatges viatges) {
        this.viatges = viatges;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservesPK != null ? reservesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserves)) {
            return false;
        }
        Reserves other = (Reserves) object;
        if ((this.reservesPK == null && other.reservesPK != null) || (this.reservesPK != null && !this.reservesPK.equals(other.reservesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domini.Reserves[ reservesPK=" + reservesPK + " ]";
    }
    
}
