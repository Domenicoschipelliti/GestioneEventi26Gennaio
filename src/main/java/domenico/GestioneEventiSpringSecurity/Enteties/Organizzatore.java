package domenico.GestioneEventiSpringSecurity.Enteties;

import domenico.GestioneEventiSpringSecurity.ENUM.RUOLI;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@ToString
public class Organizzatore implements UserDetails {
    @Id
    @GeneratedValue
    private UUID organizzatoreId;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private RUOLI ruolo;

    @ManyToMany
    @JoinTable(name = "eventi_organizzati",joinColumns=@JoinColumn( name = "organizzatoreId"),inverseJoinColumns = @JoinColumn(name = "eventiId"))
    private List<Eventi> eventiGestiti;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.ruolo.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRuolo(RUOLI ruolo) {
        this.ruolo = ruolo;
    }

    public void setEventiGestiti(List<Eventi> eventiGestiti) {
        this.eventiGestiti = eventiGestiti;
    }
}
