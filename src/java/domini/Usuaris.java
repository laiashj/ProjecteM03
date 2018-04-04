package domini;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * @author Laia Sanahuja
 */
@Entity
@Table(name = "USUARIS")
@NamedQueries({
    @NamedQuery(name = "Usuaris.findAll", query = "SELECT u FROM Usuaris u")
    , @NamedQuery(name = "Usuaris.findByNif", query = "SELECT u FROM Usuaris u WHERE u.nif = :nif")
    , @NamedQuery(name = "Usuaris.findByNom", query = "SELECT u FROM Usuaris u WHERE u.nom = :nom")
    , @NamedQuery(name = "Usuaris.findByDatanaixement", query = "SELECT u FROM Usuaris u WHERE u.datanaixement = :datanaixement")
    , @NamedQuery(name = "Usuaris.findByTelefon", query = "SELECT u FROM Usuaris u WHERE u.telefon = :telefon")
    , @NamedQuery(name = "Usuaris.findByCorreu", query = "SELECT u FROM Usuaris u WHERE u.correu = :correu")})
public class Usuaris implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "NIF")
    private String nif;
    @Size(max = 80)
    @Column(name = "NOM")
    private String nom;
    @Column(name = "DATANAIXEMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datanaixement;
    @Size(max = 9)
    @Column(name = "TELEFON")
    private String telefon;
    @Size(max = 20)
    @Column(name = "CORREU")
    private String correu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuaris")
    private List<Reserves> reservesList;

    public Usuaris() {
    }

    public Usuaris(String nif, String nom, Date datanaixement, String telefon, String correu) {
        this.nif = nif;
        this.nom = nom;
        this.datanaixement = datanaixement;
        this.telefon = telefon;
        this.correu = correu;
    }
    
    

    public Usuaris(String nif) {
        this.nif = nif;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDatanaixement() {
        return datanaixement;
    }

    public void setDatanaixement(Date datanaixement) {
        this.datanaixement = datanaixement;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getCorreu() {
        return correu;
    }

    public void setCorreu(String correu) {
        this.correu = correu;
    }

    public List<Reserves> getReservesList() {
        return reservesList;
    }

    public void setReservesList(List<Reserves> reservesList) {
        this.reservesList = reservesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nif != null ? nif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuaris)) {
            return false;
        }
        Usuaris other = (Usuaris) object;
        if ((this.nif == null && other.nif != null) || (this.nif != null && !this.nif.equals(other.nif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nif = " + nif + ", nom = " + nom + ", telefon = " + telefon + ", correu = " + correu +"   ";
    }

    
    
}
