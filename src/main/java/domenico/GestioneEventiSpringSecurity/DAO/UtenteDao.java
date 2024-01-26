package domenico.GestioneEventiSpringSecurity.DAO;

import domenico.GestioneEventiSpringSecurity.Enteties.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UtenteDao extends JpaRepository<Utente,UUID> {
}
