package domenico.GestioneEventiSpringSecurity.ECCEZIONI;

public class Errore401 extends RuntimeException {
    public Errore401(String nonAutorizzato){
        super(nonAutorizzato);
    }
}
