package domenico.GestioneEventiSpringSecurity.SERVICE;

import domenico.GestioneEventiSpringSecurity.DAO.OrganizzatoreDao;
import domenico.GestioneEventiSpringSecurity.ECCEZIONI.IdNonTRovato;
import domenico.GestioneEventiSpringSecurity.ENUM.RUOLI;
import domenico.GestioneEventiSpringSecurity.Enteties.Organizzatore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrganizzatoreService {

    @Autowired
    private OrganizzatoreDao organizzatoreDao;


    public Page<Organizzatore> listaOrganizzatori(int size, int page, String order){
        Pageable organizzatori= PageRequest.of(size,page, Sort.by(order));

        return organizzatoreDao.findAll(organizzatori);
    }

    //GET/PUT/PATCH
    public Organizzatore organizzatoreNonTrovato(UUID organizzatoreId){
        return organizzatoreDao.findById(organizzatoreId).orElseThrow(()->new IdNonTRovato(organizzatoreId));
    }

    //PUT
    public Organizzatore organizzatoreAggiornato(UUID organizzatoreId,Organizzatore organizzatoreSet){
        Organizzatore aggiornamento=this.organizzatoreNonTrovato(organizzatoreId);
        aggiornamento.setNome(organizzatoreSet.getNome());
        aggiornamento.setCognome(organizzatoreSet.getCognome());
        aggiornamento.setEmail(organizzatoreSet.getEmail());
        aggiornamento.setPassword(organizzatoreSet.getPassword());
        aggiornamento.setRuolo(RUOLI.ORGANIZZATORE);
        aggiornamento.setEventiGestiti(organizzatoreSet.getEventiGestiti());

        return organizzatoreDao.save(aggiornamento);
    }


    //DELETE
    public void  organizzatoreEliminato(UUID organizzatoreId){
        Organizzatore organizzatoreCancellato=this.organizzatoreNonTrovato(organizzatoreId);
        organizzatoreDao.delete(organizzatoreCancellato);
    }
}
