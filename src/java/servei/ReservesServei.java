package servei;

import com.sun.xml.ws.api.tx.at.Transactional;
import dao.ReservesFacade;
import domini.Reserves;
import domini.ReservesPK;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;

/*
 * @author Laia Sanahuja
 */
@Named(value = "reservesServei")
@SessionScoped
public class ReservesServei implements Serializable {

    @Inject
    private ReservesFacade reservaDao;

    @Transactional
    public List<Reserves> llistarReserves() {
        return reservaDao.findAll();
    }

    @Transactional
    public void inserirReserva(Reserves reserva) {
        reservaDao.create(reserva);
    }

    @Transactional
    public Reserves obtenirReserva (ReservesPK reservesPK) {
        return reservaDao.find(reservesPK);
    }

    @Transactional
    public void modificarReserva(Reserves reserva) {
        reservaDao.edit(reserva);
    }

    @Transactional
    public void remove(ReservesPK reservaPK) {
        reservaDao.remove(reservaDao.find(reservaPK));
    }

}
