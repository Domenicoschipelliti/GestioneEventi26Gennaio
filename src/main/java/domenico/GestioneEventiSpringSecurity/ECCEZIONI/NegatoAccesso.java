package domenico.GestioneEventiSpringSecurity.ECCEZIONI;

import java.time.LocalDate;

public class NegatoAccesso extends RuntimeException{
  public NegatoAccesso(String messaggio, LocalDate giornata){
      super(messaggio);
  }

}
