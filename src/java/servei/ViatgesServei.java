package servei;


import dao.ViatgesFacade;
import domini.Reserves;
import domini.Viatges;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;

/*
 * @author Laia Sanahuja
 */
@Named(value = "viatgesServei")
@SessionScoped
public class ViatgesServei implements Serializable {
    @Inject
    private ViatgesFacade viatgeDao;
    
    @Transactional
    public List<Viatges> llistarViatges() {
        return viatgeDao.findAll();
    }
    
    @Transactional
    public void inserirViatge (Viatges viatge) {
        viatgeDao.create(viatge);
    }
    
    @Transactional
    public Viatges obtenirViatge (Integer codi) {
        return viatgeDao.find(codi);
    }

    @Transactional
    public void modificarViatge(Viatges viatge) {
        viatgeDao.edit(viatge);
    }

    @Transactional
    public void remove(Integer codi) {
        viatgeDao.remove(viatgeDao.find(codi));
    }
    
    @Transactional
    public List<Reserves> llistarReserves(Integer codi){
        return viatgeDao.find(codi).getReservesList();
    }

    
    
    
}
