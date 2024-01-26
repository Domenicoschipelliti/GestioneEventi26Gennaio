package domenico.GestioneEventiSpringSecurity.ECCEZIONI;

import java.util.UUID;

public class IdNonTRovato extends RuntimeException{
    public IdNonTRovato(String message) {
        super(message);
    }
    public IdNonTRovato(UUID id){
        super("utente con id "+id+" non trovato");
    }
}

