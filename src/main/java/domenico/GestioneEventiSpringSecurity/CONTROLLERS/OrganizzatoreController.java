package domenico.GestioneEventiSpringSecurity.CONTROLLERS;

import domenico.GestioneEventiSpringSecurity.Enteties.Organizzatore;
import domenico.GestioneEventiSpringSecurity.SERVICE.OrganizzatoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public class OrganizzatoreController {
    @Autowired
    private OrganizzatoreService organizzatoreService;
    @GetMapping
    public Page<Organizzatore> listaOrganizzatori(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "8")int size, @RequestParam(defaultValue = "organizzatoreId")String order){
        return organizzatoreService.listaOrganizzatori(page,size,order);
    }

    //GET(ID)
    @GetMapping("/{organizzatoreId}")
    @ResponseStatus(HttpStatus.OK)
    public Organizzatore organizzatoreTrovato(@PathVariable UUID organizzatoreId){
        return organizzatoreService.organizzatoreNonTrovato(organizzatoreId);
    }




    //PUT(ID+BODY)
    @PutMapping("/{organizzatoreId}")
    public Organizzatore organizzatorePut(@PathVariable UUID organizzatoreId,@RequestBody Organizzatore organizzatoreBody ){
        return organizzatoreService.organizzatoreAggiornato(organizzatoreId,organizzatoreBody);
    }


    //DELETE(ID)
    @DeleteMapping("/{organizzatoreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void utenteDelete(@PathVariable UUID organizzatoreId){
        organizzatoreService.organizzatoreEliminato(organizzatoreId);
    }
}
