package domenico.GestioneEventiSpringSecurity.ECCEZIONI;

import domenico.GestioneEventiSpringSecurity.ERRORI.ErroriConLista;
import domenico.GestioneEventiSpringSecurity.ERRORI.ErroriSenzaLista;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorHandLer {
    @ExceptionHandler(BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroriConLista erroriConLista(BadRequest e) {
        List<String> messagiErronei = new ArrayList<>();
        if (e.getErroriMessaggi() != null)
            messagiErronei = e.getErroriMessaggi().stream().map(err -> err.getDefaultMessage()).toList();
        return new ErroriConLista(e.getMessage(), LocalDate.now(), messagiErronei);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErroriSenzaLista nonAutorizzato(Errore401 errore401){
        return  new ErroriSenzaLista("l'utente non è autorizzato",LocalDate.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErroriSenzaLista accessoNegato(NegatoAccesso accessoNegato){
        return new ErroriSenzaLista("accesso negato riprova a fare il login",LocalDate.now());
    }

    @ExceptionHandler(IdNonTRovato.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroriSenzaLista erroriPossibili2Utente(IdNonTRovato e) {
        return new ErroriSenzaLista(e.getMessage(), LocalDate.now());
    }




    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroriSenzaLista erroriPossibili2ServerProblem(Exception e) {
        e.printStackTrace();
        return new ErroriSenzaLista("Errore generico, risolveremo il prima possibile", LocalDate.now());
    }

}
