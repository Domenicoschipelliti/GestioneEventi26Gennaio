package domenico.GestioneEventiSpringSecurity.CONTROLLERS;

import domenico.GestioneEventiSpringSecurity.DTO.RispostaAllUtente;
import domenico.GestioneEventiSpringSecurity.DTO.UtenteDTO;
import domenico.GestioneEventiSpringSecurity.DTO.UtenteLoginRisposta;
import domenico.GestioneEventiSpringSecurity.ECCEZIONI.BadRequest;
import domenico.GestioneEventiSpringSecurity.Enteties.Utente;
import domenico.GestioneEventiSpringSecurity.SERVICE.SicurezzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lucchetto")
public class SicurezzaController {
    @Autowired
    private SicurezzaService sicurezzaService;

    @PostMapping("/login")
    public UtenteLoginRisposta loginUtente(@RequestBody UtenteDTO utenteLogin){
        String tokenAccesso=sicurezzaService.autenticazioneUtente(utenteLogin);
        return new UtenteLoginRisposta(tokenAccesso);
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RispostaAllUtente RegistrazioneUtente(@RequestBody @Validated UtenteDTO utenteBody, BindingResult validazione){
        if (validazione.hasErrors()){
            throw new BadRequest(validazione.getAllErrors());
        }
        Utente ute=sicurezzaService.save(utenteBody);
        return new RispostaAllUtente(ute.getUtenteId());
    }
}
