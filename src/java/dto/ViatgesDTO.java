package dto;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/*
 * @author Laia Sanahuja 
 */
@Named
@SessionScoped
public class ViatgesDTO implements Serializable {

    private Integer codi;
    private String tipus;
    private String descripcio;
    private Date dataInici;
    private Date dataFi;
    private BigDecimal preu;
    private StreamedContent foto;
    private UploadedFile arxiuFoto;
    private Short placesOfertades;
    private Short placesReservades;
   // private final String rangDates;

    public ViatgesDTO() {
     
    }

    public ViatgesDTO(Integer codi) {
        //this();
        this.codi = codi;
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

    public Date getDataInici() {
        return dataInici;
    }

    public void setDataInici(Date dataInici) {
        this.dataInici = dataInici;
    }

    public Date getDataFi() {
        return dataFi;
    }

    public void setDataFi(Date dataFi) {
        this.dataFi = dataFi;
    }

    public BigDecimal getPreu() {
        return preu;
    }

    public void setPreu(BigDecimal preu) {
        this.preu = preu;
    }

    public StreamedContent getFoto() {
        return foto;
    }

    public void setFoto(StreamedContent foto) {
        this.foto = foto;
    }

    public UploadedFile getArxiuFoto() {
        return arxiuFoto;
    }

    public void setArxiuFoto(UploadedFile arxiuFoto) {
        this.arxiuFoto = arxiuFoto;
    }

    public Short getPlacesOfertades() {
        return placesOfertades;
    }

    public void setPlacesOfertades(Short placesOfertades) {
        this.placesOfertades = placesOfertades;
    }

    public Short getPlacesReservades() {
        return placesReservades;
    }

    public void setPlacesReservades(Short placesReservades) {
        this.placesReservades = placesReservades;
    }

    
    
    
}
