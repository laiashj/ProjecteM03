package dto;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/*
 * @author Laia Sanahuja
 */

@Named
@SessionScoped
public class ReservesDTO implements Serializable{
    
    private String nif;
    private int codiViatge;
    private Short placesReservades;

    public ReservesDTO() {
    }

    public ReservesDTO(String nif, int codiViatge) {
        this.nif = nif;
        this.codiViatge = codiViatge;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public int getCodiViatge() {
        return codiViatge;
    }

    public void setCodiViatge(int codiViatge) {
        this.codiViatge = codiViatge;
    }

    public Short getPlacesReservades() {
        return placesReservades;
    }

    public void setPlacesReservades(Short placesReservades) {
        this.placesReservades = placesReservades;
    }
    
    
}
