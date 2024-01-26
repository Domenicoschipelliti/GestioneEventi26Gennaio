package domenico.GestioneEventiSpringSecurity.DAO;

import domenico.GestioneEventiSpringSecurity.Enteties.Eventi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventiDao extends JpaRepository<Eventi,UUID> {
}
