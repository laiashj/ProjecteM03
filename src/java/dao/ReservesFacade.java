/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domini.Reserves;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Admin
 */
@Named
@SessionScoped
public class ReservesFacade extends AbstractFacade<Reserves> implements Serializable{

    @PersistenceContext(unitName = "AgenciaViatgesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservesFacade() {
        super(Reserves.class);
    }
    
}
