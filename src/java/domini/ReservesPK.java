/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domini;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Embeddable
public class ReservesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "NIF")
    private String nif;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIVIATGE")
    private int codiviatge;

    public ReservesPK() {
    }

    public ReservesPK(String nif, int codiviatge) {
        this.nif = nif;
        this.codiviatge = codiviatge;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public int getCodiviatge() {
        return codiviatge;
    }

    public void setCodiviatge(int codiviatge) {
        this.codiviatge = codiviatge;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.nif);
        hash = 89 * hash + this.codiviatge;
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
        final ReservesPK other = (ReservesPK) obj;
        if (this.codiviatge != other.codiviatge) {
            return false;
        }
        if (!Objects.equals(this.nif, other.nif)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "nif =" + nif + ", codiviatge = " + codiviatge + " " ;
    }
    
}
