/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domini.Usuaris;
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
public class UsuarisFacade extends AbstractFacade<Usuaris> implements Serializable{

    @PersistenceContext(unitName = "AgenciaViatgesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarisFacade() {
        super(Usuaris.class);
    }
    
}
