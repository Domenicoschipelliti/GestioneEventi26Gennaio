package domenico.GestioneEventiSpringSecurity.CONTROLLERS;

import domenico.GestioneEventiSpringSecurity.Enteties.Eventi;
import domenico.GestioneEventiSpringSecurity.SERVICE.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/eventi")
public class EventiController {
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public Page<Eventi> listaEventi(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "30")int size, @RequestParam(defaultValue = "eventoId")String order){
        return eventoService.trovaEventi(page,size,order);
    }

    @GetMapping("/{eventoId}")
    @ResponseStatus(HttpStatus.OK)
    public Eventi trovaEvento(@PathVariable UUID eventoId){
        return eventoService.eventoTrovato(eventoId);
    }


    @PutMapping("/{eventoId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Eventi eventoAggiornato(@PathVariable UUID eventoId,@RequestBody Eventi eventoBody ){
        return eventoService.eventoAggiornato(eventoId,eventoBody);
    }


    //DELETE(ID)
    @DeleteMapping("/{eventoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority(ORGANIZZATORE)")
    public void eventoDelete(@PathVariable UUID eventoId){
        eventoService.eventoCancellato(eventoId);
    }



}
