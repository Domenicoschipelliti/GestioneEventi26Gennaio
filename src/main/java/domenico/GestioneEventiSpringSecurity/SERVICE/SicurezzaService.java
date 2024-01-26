package domenico.GestioneEventiSpringSecurity.SERVICE;

import domenico.GestioneEventiSpringSecurity.DAO.UtenteDao;
import domenico.GestioneEventiSpringSecurity.DTO.UtenteDTO;
import domenico.GestioneEventiSpringSecurity.ECCEZIONI.BadRequest;
import domenico.GestioneEventiSpringSecurity.ECCEZIONI.Errore401;
import domenico.GestioneEventiSpringSecurity.ENUM.RUOLI;
import domenico.GestioneEventiSpringSecurity.Enteties.Utente;
import domenico.GestioneEventiSpringSecurity.SICUREZZA.TokenJwtSSicurezza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SicurezzaService {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private TokenJwtSSicurezza JwTs;

    @Autowired
    private UtenteDao utenteDao;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public String autenticazioneUtente(UtenteDTO utente){
        Utente utente1=utenteService.cercaEmail(utente.email());
        if (passwordEncoder.matches(utente1.getPassword(),utente.password())){
            return JwTs.tokenCreation(utente1);
        }else {
            throw new Errore401("l'utente non è autorizzato, la preghiamo di autenticarsi");
        }
    }

    //POST
    public Utente save(UtenteDTO utenteDTO){

        utenteDao.cercaEmail(utenteDTO.email()).ifPresent(utente->{
            throw new BadRequest("l'email " +utente.getEmail()+" è già in uso");
        });

        Utente utente=new Utente();

        utente.setNome(utenteDTO.nome());
        utente.setCognome(utenteDTO.cognome());
        utente.setEmail(utenteDTO.email());
        utente.setPassword(passwordEncoder.encode(utenteDTO.password()));
        utente.setRuolo(RUOLI.UTENTE);


        return  utenteDao.save(utente);

    }
}
