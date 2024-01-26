package domenico.GestioneEventiSpringSecurity.SICUREZZA;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfigurazioneSicurezza {
    @Bean
    SecurityFilterChain filtroDiSucurezza(HttpSecurity sicurezza) throws Exception {

        sicurezza.formLogin(AbstractHttpConfigurer::disable);//disabilitazione del login di default di spring security
        sicurezza.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));//interruzioni delle sessioni
        sicurezza.csrf(AbstractHttpConfigurer::disable);//sistema di protezione della comunicazione tra back e front end.

        sicurezza.authorizeHttpRequests(richiesta->richiesta.requestMatchers("/**").permitAll());

        return sicurezza.build();

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

}
