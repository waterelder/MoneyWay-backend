package xyz.trackbuck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import xyz.trackbuck.security.AuthConfig;
import xyz.trackbuck.security.TokenAuthenticationFilter;
import xyz.trackbuck.security.TokenAuthenticationProvider;

/**
 * Created by lex on 07.10.16.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan(basePackages = {"xyz.trackbuck.security.*", "xyz.trackbuck.domain.service.security.*"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private TokenAuthenticationFilter tokenFilter;


    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf()
                .disable()

                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(tokenAuthenticationProvider)
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, AuthConfig.SECURED_URL + "**").permitAll()
                .antMatchers(AuthConfig.SECURED_URL + "**")
                .authenticated();


    }

}

