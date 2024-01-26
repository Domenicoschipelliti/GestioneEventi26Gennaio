package domenico.GestioneEventiSpringSecurity.DAO;

import domenico.GestioneEventiSpringSecurity.Enteties.Organizzatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrganizzatoreDao extends JpaRepository<Organizzatore,UUID> {
}
