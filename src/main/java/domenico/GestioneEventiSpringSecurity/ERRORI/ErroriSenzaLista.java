package domenico.GestioneEventiSpringSecurity.ERRORI;

import java.time.LocalDate;

public record ErroriSenzaLista(String messaggio, LocalDate giornata) {
    public ErroriSenzaLista(String messaggio,LocalDate giornata){
        this.messaggio=messaggio;
        this.giornata=giornata;

    }
}
