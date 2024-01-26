package domenico.GestioneEventiSpringSecurity.ERRORI;

import java.time.LocalDate;
import java.util.List;

public record ErroriConLista(String messaggio, LocalDate giornata, List<String> erroriDellaPost) {
}
