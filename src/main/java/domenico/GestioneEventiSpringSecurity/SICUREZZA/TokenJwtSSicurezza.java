package domenico.GestioneEventiSpringSecurity.SICUREZZA;

import domenico.GestioneEventiSpringSecurity.ECCEZIONI.Errore401;
import domenico.GestioneEventiSpringSecurity.Enteties.Organizzatore;
import domenico.GestioneEventiSpringSecurity.Enteties.Utente;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class TokenJwtSSicurezza {
    @Value("${spring_JwT_key}")
    private String codiceTopSecret;

    public String tokenCreation(Utente codiceUtente){
        return Jwts.builder()
                .subject(String.valueOf(codiceUtente.getUtenteId()))
                .issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis()+1000*60*60*24*14))
                .signWith(Keys.hmacShaKeyFor(codiceTopSecret.getBytes())).compact();
    }

    public String tokenCreation2(Organizzatore codiceAdmin){
        return Jwts.builder()
                .subject(String.valueOf(codiceAdmin.getOrganizzatoreId()))
                .issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis()+1000*60*60*24*21))
                .signWith(Keys.hmacShaKeyFor(codiceTopSecret.getBytes())).compact();
    }

    public void verificaToken(String token){
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(codiceTopSecret.getBytes())).build().parse(token);
        } catch (Exception ex) {
            throw new Errore401("Il token sembra non sia valido riprova a loggarti");
        }
    }

    public String estraiToken(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(codiceTopSecret.getBytes()))
                .build()
                .parseSignedClaims(token).getPayload().getSubject();
    }
}
