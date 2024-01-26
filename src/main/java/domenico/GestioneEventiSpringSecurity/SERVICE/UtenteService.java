package domenico.GestioneEventiSpringSecurity.SERVICE;

import domenico.GestioneEventiSpringSecurity.DAO.UtenteDao;
import domenico.GestioneEventiSpringSecurity.ECCEZIONI.IdNonTRovato;
import domenico.GestioneEventiSpringSecurity.ENUM.RUOLI;
import domenico.GestioneEventiSpringSecurity.Enteties.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtenteService {
    @Autowired
    private UtenteDao utenteDao;

    public Page<Utente> TrovaUtente(int size, int page, String order){
        Pageable impaginazione= PageRequest.of(size,page, Sort.by(order));

        return utenteDao.findAll(impaginazione);
    }

    public Utente utenteIdTrovato(UUID utenteId){
        return utenteDao.findById(utenteId).orElseThrow(()->new IdNonTRovato(utenteId));
    }

    public Utente aggiornaUtente(UUID utenteId,Utente utenteSet){
        Utente aggiornamento=this.utenteIdTrovato(utenteId);
        aggiornamento.setNome(utenteSet.getNome());
        aggiornamento.setCognome(utenteSet.getCognome());
        aggiornamento.setEmail(utenteSet.getEmail());
        aggiornamento.setRuolo(RUOLI.UTENTE);
        return utenteDao.save(aggiornamento);
    }


    //DELETE
    public void  cancellaUtente(UUID utenteId){
        Utente utenteCancellato=this.utenteIdTrovato(utenteId);
        utenteDao.delete(utenteCancellato);
    }

}
