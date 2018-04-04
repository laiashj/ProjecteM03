package controlador;

import domini.Usuaris;
import dto.UsuarisDTO;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import servei.UsuarisServei;

/*
 * @author Laia Sanahuja
 */
public class controladorUsuaris {
    @Inject
    private UsuarisServei serveiUsuari;
    
    @Inject
    private UsuarisDTO usuariActual;
    
    //Metodes Usuari 
    public List<Usuaris> llistarUsuaris() {
        List<Usuaris> usuaris = serveiUsuari.llistarUsuaris();
        usuaris.sort(Comparator.comparing(Usuaris::getNif));
        return usuaris;
    }

    public String preperarInsercioUsuari() {
        netejarFormulariUsuari();
        return "FormulariInsercioUsuari";
    }

    public String crearUsuari(String nif, String nom, Date dataNaixement, String correu, String telefon) {
        Usuaris u = new Usuaris(nif, nom, dataNaixement, correu, telefon);
        serveiUsuari.inserirUsuari(u);
        return "index";
    }

    public Usuaris obtenirUsuari(String nif) {
        Usuaris u = serveiUsuari.obtenirUsuari(nif);
        return u;
    }

    public String obtenirUsuariConsulta(String nif) {
        Usuaris u = serveiUsuari.obtenirUsuari(nif);
        return "consultaUsuari";
    }

    public String eliminarUsuari(String nif) {
        serveiUsuari.remove(nif);
        return "index";
    }

    public UsuarisDTO getUsuariActual() {
        return usuariActual;
    }

    public void setUsuariActual(UsuarisDTO usuariActual) {
        this.usuariActual = usuariActual;
    }

    public UsuarisServei getServeiUsuari() {
        return serveiUsuari;
    }

    public void setServeiUsuari(UsuarisServei serveiUsuari) {
        this.serveiUsuari = serveiUsuari;
    }
//Metodes privats usuaris

    private void passarUsuarisUsuarisDTO(Usuaris u) {
        usuariActual.setNif(u.getNif());
        usuariActual.setNom(u.getNom());
        usuariActual.setDataNaixement(u.getDatanaixement());
        usuariActual.setCorreu(u.getCorreu());
        usuariActual.setTelefon(u.getTelefon());
    }

    private void passarUsuarisDTOUsusaris(Usuaris u) {
        u.setNif(usuariActual.getNif());
        u.setNom(usuariActual.getNom());
        u.setDatanaixement(usuariActual.getDataNaixement());
        u.setCorreu(usuariActual.getCorreu());
        u.setTelefon(usuariActual.getTelefon());
    }

    private void netejarFormulariUsuari() {
        usuariActual.setNif(null);
        usuariActual.setNom(null);
        usuariActual.setDataNaixement(null);
        usuariActual.setCorreu(null);
        usuariActual.setTelefon(null);
    }
}
