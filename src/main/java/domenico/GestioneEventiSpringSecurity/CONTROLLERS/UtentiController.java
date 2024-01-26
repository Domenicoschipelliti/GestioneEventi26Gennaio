package domenico.GestioneEventiSpringSecurity.CONTROLLERS;

import domenico.GestioneEventiSpringSecurity.Enteties.Utente;
import domenico.GestioneEventiSpringSecurity.SERVICE.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UtentiController {
   @Autowired
   private UtenteService utenteService;

    @GetMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Page<Utente> trovaUtenti(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10")int size, @RequestParam(defaultValue = "utenteId")String order){
        return utenteService.TrovaUtente(page,size,order);
    }

    @PutMapping("/{utenteId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Utente utenteAggiornato(@PathVariable UUID utenteId, @RequestBody Utente utenteBody ){
        return utenteService.aggiornaUtente(utenteId,utenteBody);
    }

    @DeleteMapping("/{utenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority(ORGANIZZATORE)")
    public void utenteCancellato(@PathVariable UUID utenteId){
        utenteService.cancellaUtente(utenteId);
    }


}
