package domenico.GestioneEventiSpringSecurity.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record OrganizzatoreDTO(@NotEmpty(message = "Mettere il nome ci aiuterà ad identificarti")
                               @Size(min = 2,max = 20,message = "la lunghezza del nome va dai 2 a i 20 caratteri di solito")
                               String nome,
                               @NotEmpty(message = "il cognome ha la stessa importanza del nome")
                               String cognome,
                               @NotEmpty(message = "L'email ci aiuterà a mandarti il codice del dispositivo che ti verrà dato")
                               @Email(message = "la mail messa non esiste")
                               String email,@NotEmpty(message = "il campo password non deve essere vuoto") String password) {
}
