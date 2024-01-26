package domenico.GestioneEventiSpringSecurity.Enteties;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@ToString
@Table(name = "utente_id")
public class Eventi {
    @Id
    @GeneratedValue
    private UUID eventoId;
    private String titolo;
    private String descrizione;
    private String luogo;
    private LocalDate dataEvento;
    private int numeroPostiDisponibili;

    @ManyToOne
    @JoinColumn(name = "utente")
    private Utente utente;

    @ManyToMany(mappedBy = "eventiGestiti")
    private List<Organizzatore> organizzatori;


    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public void setNumeroPostiDisponibili(int numeroPostiDisponibili) {
        this.numeroPostiDisponibili = numeroPostiDisponibili;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public void setOrganizzatori(List<Organizzatore> organizzatori) {
        this.organizzatori = organizzatori;
    }
}
