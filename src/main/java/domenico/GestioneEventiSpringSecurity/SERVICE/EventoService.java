package domenico.GestioneEventiSpringSecurity.SERVICE;

import domenico.GestioneEventiSpringSecurity.DAO.EventiDao;
import domenico.GestioneEventiSpringSecurity.ECCEZIONI.IdNonTRovato;
import domenico.GestioneEventiSpringSecurity.Enteties.Eventi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventoService {

    @Autowired
    private EventiDao eventiDao;

    public Page<Eventi> trovaEventi(int size, int page, String order){
        Pageable lista= PageRequest.of(size,page, Sort.by(order));

        return eventiDao.findAll(lista);
    }


    public Eventi eventoTrovato(UUID eventoId){
        return eventiDao.findById(eventoId).orElseThrow(()->new IdNonTRovato(eventoId));
    }

    //PUT
    public Eventi eventoAggiornato(UUID eventoId,Eventi eventoModificato){
        Eventi aggiornamento=this.eventoTrovato(eventoId);
        aggiornamento.setTitolo(eventoModificato.getTitolo());
        aggiornamento.setDataEvento(eventoModificato.getDataEvento());
        aggiornamento.setDescrizione(eventoModificato.getDescrizione());
        aggiornamento.setLuogo(eventoModificato.getLuogo());
        aggiornamento.setNumeroPostiDisponibili(eventoModificato.getNumeroPostiDisponibili());
        aggiornamento.setOrganizzatori(eventoModificato.getOrganizzatori());


        return eventiDao.save(aggiornamento);
    }


    //DELETE
    public void  eventoCancellato(UUID eventoId){
        Eventi cancellazione=this.eventoTrovato(eventoId);
        eventiDao.delete(cancellazione);
    }



}
