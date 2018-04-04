package controlador;

import domini.Reserves;
import domini.ReservesPK;
import dto.ReservesDTO;
import java.util.List;
import javax.inject.Inject;
import servei.ReservesServei;

/*
 * @author Laia Sanahuja
 */
public class controladorReserves {

    @Inject
    private ReservesServei serveiReserva;
    @Inject
    private ReservesDTO reservaActual;

    //Metodes Reserves
    public List<Reserves> llistarReserves() {
        List<Reserves> reserves = serveiReserva.llistarReserves();

        return reserves;
    }

    public String preperarInsercioReserves() {
        netejarFormulariReserves();
        return "FormulariInsercioReserva";
    }

    public String crearReserva(String nif, Integer codiviatge, short placesreservades) {
        Reserves r = new Reserves(nif, codiviatge, placesreservades);
        serveiReserva.inserirReserva(r);
        return "index";
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
