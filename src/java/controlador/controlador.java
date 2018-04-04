package controlador;

import domini.*;
import dto.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import servei.*;


/*
 * @author Laia Sanahuja
 */
@Named(value = "controlador")
@SessionScoped
public class controlador implements Serializable {

    @Inject
    private UsuarisServei serveiUsuari;
    @Inject
    private ViatgesServei serveiViatge;
    @Inject
    private ReservesServei serveiReserva;

    @Inject
    private UsuarisDTO usuariActual;
    @Inject
    private ViatgesDTO viatgeActual;
    @Inject
    private ReservesDTO reservaActual;

//Metodes Usuari 
    public List<Usuaris> llistarUsuaris() {
        List<Usuaris> usuaris = serveiUsuari.llistarUsuaris();
        usuaris.sort(Comparator.comparing(Usuaris::getNif));
        return usuaris;
    }

    public String prepararInsercioUsuari() {
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
//Metodes Viatges

    public List<Viatges> llistarViatges() {
        List<Viatges> viatges = serveiViatge.llistarViatges();
        viatges.sort(Comparator.comparing(Viatges::getCodi));
        return viatges;
    }

    public List<Reserves> llistarReservesViatge(Integer codi) {
        List<Reserves> reservesViatge = serveiViatge.llistarReserves(codi);
        return reservesViatge;
    }

    public List<String> llistarTipusViatge() {
        List<String> tipus = new ArrayList<>();
        List<Viatges> viatges = this.llistarViatges();
        for (Viatges viatge : viatges) {
            if (!tipus.contains(viatge.getTipus().toUpperCase())) {
                tipus.add(viatge.getTipus().toUpperCase());
            }
        }
        return tipus;
    }

    public List<Viatges> llistarViatges(String tipus) {
        List<Viatges> tots = this.llistarViatges();
        List<Viatges> viatges = new ArrayList<>();
        for (Viatges tot : tots) {
            if (tot.getTipus().equalsIgnoreCase(tipus)) {
                viatges.add(tot);
            }
        }
        return viatges;
    }

    public String prepararInsercioViatge() {
        netejarFormulariViatge();
        return "FormulariInsercioViatge";
    }

    public String crearViatge(String tipus, String descripcio, Date datainici, Date datafi, BigDecimal preu, Short placesofertades) {
        byte[] foto;
        Viatges v = new Viatges(tipus, descripcio, datainici, datafi, preu, placesofertades);
        if (viatgeActual.getArxiuFoto() != null) {
            try {
                foto = IOUtils.toByteArray(viatgeActual.getArxiuFoto().getInputstream());
                v.setFoto(foto);
            } catch (IOException ex) {
                Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        serveiViatge.inserirViatge(v);
        return "index";
    }

    public String obtenirViatgeConsulta(Integer codi) {
        Viatges v = serveiViatge.obtenirViatge(codi);
        return "consultaViatges";
    }

    public String eliminarViatge(Integer codi) {
        serveiViatge.remove(codi);
        return "index";
    }

    public ViatgesDTO getViatgeActual() {
        return viatgeActual;
    }

    public void setViatgeActual(ViatgesDTO viatgeActual) {
        this.viatgeActual = viatgeActual;
    }

    public ViatgesServei getServeiViatge() {
        return serveiViatge;
    }

    public void setServeiViatge(ViatgesServei serveiViatge) {
        this.serveiViatge = serveiViatge;
    }

    public StreamedContent conversioFoto() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the media. Return a real StreamedContent with the media bytes.
            Integer codi = Integer.parseInt(context.getExternalContext().getRequestParameterMap().get("codi"));
            byte[] image = serveiViatge.obtenirViatge(codi).getFoto();
            return new DefaultStreamedContent(new ByteArrayInputStream(image));

        }
    }

//Metodes privats viatges
    private void passarViatgesViatgesDTO(Viatges v) {
        viatgeActual.setDataInici(v.getDatainici());
        viatgeActual.setDataFi(v.getDatafi());
        viatgeActual.setDescripcio(v.getDescripcio());
        viatgeActual.setTipus(v.getTipus());
        viatgeActual.setPlacesOfertades(v.getPlacesofertades());
        viatgeActual.setPlacesReservades(v.getPlacesreservades());
        viatgeActual.setPreu(v.getPreu());
        if (v.getFoto() != null) {
            ByteArrayInputStream bis = new ByteArrayInputStream(v.getFoto());
            StreamedContent streamFoto = new DefaultStreamedContent(bis, "image/png");
            viatgeActual.setFoto(streamFoto);
        }

    }

    private void passarViatgesDTOViatges(Viatges v) {
        v.setDatainici(viatgeActual.getDataInici());
        v.setDatafi(viatgeActual.getDataFi());
        v.setDescripcio(viatgeActual.getDescripcio());
        v.setTipus(viatgeActual.getTipus());
        v.setPlacesofertades(viatgeActual.getPlacesOfertades());
        v.setPlacesreservades(viatgeActual.getPlacesReservades());
        v.setPreu(viatgeActual.getPreu());
    }

    private void netejarFormulariViatge() {
        viatgeActual.setCodi(0);
        viatgeActual.setTipus("");
        viatgeActual.setDataInici(null);
        viatgeActual.setDataFi(null);
        viatgeActual.setDescripcio("");
        viatgeActual.setPreu(null);
        viatgeActual.setPlacesOfertades(null);
        viatgeActual.setPlacesReservades(null);
        viatgeActual.setFoto(null);
    }

//Metodes Reserves
    public List<Reserves> llistarReserves() {
        List<Reserves> reserves = serveiReserva.llistarReserves();

        return reserves;
    }

    public String prepararInsercioReserves(int codiViatge) {
        netejarFormulariReserves();
        reservaActual.setCodiViatge(codiViatge);
        return "FormulariInsercioReserves";
    }

    public String crearReserva(String nif, Integer codiviatge, short placesreservades) {
        Reserves r = new Reserves(nif, codiviatge, placesreservades);
        //aumentar les places reservades del viatge
        serveiReserva.inserirReserva(r);
        return "index";
    }

    public String validarNifReserva(String nif) {
        if (serveiUsuari.obtenirUsuari(nif) instanceof Usuaris) {
            return "FormulariInsercioReserva";
        } else {
            return "FormulariInsercioUsuari";
        }
    }

    public String obtenirReservaConsulta(String nif, Integer codiViatge) {
        ReservesPK reservesPK = new ReservesPK(nif, codiViatge);
        Reserves r = serveiReserva.obtenirReserva(reservesPK);
        return "consultaReserva";
    }

    public String eliminarReserva(String nif, Integer codiViatge) {
        ReservesPK reservesPK = new ReservesPK(nif, codiViatge);
        serveiReserva.remove(reservesPK);
        return "index";
    }

    public ReservesServei getServeiReserva() {
        return serveiReserva;
    }

    public void setServeiReserva(ReservesServei serveiReserva) {
        this.serveiReserva = serveiReserva;
    }

    public ReservesDTO getReservaActual() {
        return reservaActual;
    }

    public void setReservaActual(ReservesDTO reservaActual) {
        this.reservaActual = reservaActual;
    }

//Metodes privats reserves
    private void passarReservesReservesDTO(Reserves r) {
        reservaActual.setPlacesReservades(r.getPlacesreservades());
    }

    private void passarReservesDTOReserves(Reserves r) {
        r.setPlacesreservades(reservaActual.getPlacesReservades());
    }

    private void netejarFormulariReserves() {
        reservaActual.setCodiViatge(0);
        reservaActual.setNif("");
        reservaActual.setPlacesReservades(null);
    }
}
