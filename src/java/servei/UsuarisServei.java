package servei;

import com.sun.xml.ws.api.tx.at.Transactional;
import dao.UsuarisFacade;
import domini.Usuaris;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/*
 * @author Laia Sanahuja
 */
@Named(value = "usuarisServei")
@SessionScoped
public class UsuarisServei implements Serializable {

    @Inject
    private UsuarisFacade usuariDao;

    @Transactional
    public List<Usuaris> llistarUsuaris() {
        return usuariDao.findAll();
    }
    
    @Transactional
    public void inserirUsuari(Usuaris usuari) {
        usuariDao.create(usuari);
    }
    
    @Transactional
    public Usuaris obtenirUsuari (String nif) {
        return usuariDao.find(nif);
    }

    @Transactional
    public void modificarUsuari(Usuaris usuari) {
        usuariDao.edit(usuari);
    }

    @Transactional
    public void remove(String nif) {
        usuariDao.remove(usuariDao.find(nif));
    }

    
}
