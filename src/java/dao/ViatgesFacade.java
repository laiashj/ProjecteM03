package dao;

import domini.Viatges;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * @author Laia Sanahuja
 */
@Named
@SessionScoped
public class ViatgesFacade extends AbstractFacade<Viatges> implements Serializable{

    @PersistenceContext(unitName = "AgenciaViatgesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ViatgesFacade() {
        super(Viatges.class);
    }
    
}
