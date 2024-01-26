package domenico.GestioneEventiSpringSecurity.ECCEZIONI;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;
@Getter
public class BadRequest extends RuntimeException {
    private List<ObjectError> erroriMessaggi;

    public BadRequest(String message) {
        super(message);
    }

    public BadRequest(List<ObjectError> erroriMessaggi) {
        super("Errori nel body");
        this.erroriMessaggi = erroriMessaggi;
    }
}
