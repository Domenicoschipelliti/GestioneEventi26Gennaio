package domenico.GestioneEventiSpringSecurity.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EventoDTO(@NotEmpty(message = "il titolo serve per far capire alla gente di cosa tratta") String titolo, @NotEmpty(message = "la descrizione per capire cosa si fa")String descrizione, @NotEmpty(message = "la data per capire il giorno dell'evento")
                        LocalDate dataEvento, @NotNull(message = "il campo dei posti disponibili serve per sapere se l'evento e pieno o no")@NotEmpty(message = "non devi lasciarlo vuoto")int numeroDiEventiDisponobili) {
}
