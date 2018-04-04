package domini;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "VIATGES")
@NamedQueries({
    @NamedQuery(name = "Viatges.findAll", query = "SELECT v FROM Viatges v")
    , @NamedQuery(name = "Viatges.findByCodi", query = "SELECT v FROM Viatges v WHERE v.codi = :codi")
    , @NamedQuery(name = "Viatges.findByTipus", query = "SELECT v FROM Viatges v WHERE v.tipus = :tipus")
    , @NamedQuery(name = "Viatges.findByDescripcio", query = "SELECT v FROM Viatges v WHERE v.descripcio = :descripcio")
    , @NamedQuery(name = "Viatges.findByDatainici", query = "SELECT v FROM Viatges v WHERE v.datainici = :datainici")
    , @NamedQuery(name = "Viatges.findByDatafi", query = "SELECT v FROM Viatges v WHERE v.datafi = :datafi")
    , @NamedQuery(name = "Viatges.findByPreu", query = "SELECT v FROM Viatges v WHERE v.preu = :preu")
    , @NamedQuery(name = "Viatges.findByPlacesofertades", query = "SELECT v FROM Viatges v WHERE v.placesofertades = :placesofertades")
    , @NamedQuery(name = "Viatges.findByPlacesreservades", query = "SELECT v FROM Viatges v WHERE v.placesreservades = :placesreservades")})
public class Viatges implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "VIATGES_SEQ")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODI")
    private Integer codi;
    @Size(max = 50)
    @Column(name = "TIPUS")
    private String tipus;
    @Size(max = 200)
    @Column(name = "DESCRIPCIO")
    private String descripcio;
    @Column(name = "DATAINICI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datainici;
    @Column(name = "DATAFI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datafi;
    @Column(name = "PREU")
    private BigDecimal preu;
    @Lob
    @Column(name = "FOTO")
    private byte[] foto;
    @Column(name = "PLACESOFERTADES")
    private short placesofertades;
    @Column(name = "PLACESRESERVADES")
    private short placesreservades;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "viatges")
    private List<Reserves> reservesList;

    public Viatges() {
    }

   public Viatges(String tipus, String descripcio, Date datainici, Date datafi, BigDecimal preu, Short placesofertades) {

        this.tipus = tipus;
        this.descripcio = descripcio;
        this.datainici = datainici;
        this.datafi = datafi;
        this.preu = preu;
        this.placesofertades = placesofertades;
        this.placesreservades = 0;
    }

    public Integer getCodi() {
        return codi;
    }

    public void setCodi(Integer codi) {
        this.codi = codi;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public Date getDatainici() {
        return datainici;
    }

    public void setDatainici(Date datainici) {
        this.datainici = datainici;
    }

    public Date getDatafi() {
        return datafi;
    }

    public void setDatafi(Date datafi) {
        this.datafi = datafi;
    }

    public BigDecimal getPreu() {
        return preu;
    }

    public void setPreu(BigDecimal preu) {
        this.preu = preu;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Short getPlacesofertades() {
        return placesofertades;
    }

    public void setPlacesofertades(Short placesofertades) {
        this.placesofertades = placesofertades;
    }

    public Short getPlacesreservades() {
        return placesreservades;
    }

    public void setPlacesreservades(Short placesreservades) {
        this.placesreservades = placesreservades;
    }

    public List<Reserves> getReservesList() {
        return reservesList;
    }

    public void setReservesList(List<Reserves> reservesList) {
        this.reservesList = reservesList;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.codi);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Viatges other = (Viatges) obj;
        if (!Objects.equals(this.codi, other.codi)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "domini.Viatges[ codi=" + codi + " ]";
    }

}
