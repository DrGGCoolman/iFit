package de.gowlr.allcar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import de.gowlr.allcar.services.FilterService;
import de.gowlr.allcar.services.SearchService;
import de.gowlr.allcar.services.StorageService;
import de.gowlr.allcar.services.UserAdapterService;
import de.gowlr.allcar.web.CarFilterModel;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public AuthenticationSuccessHandler authSuccHandler() {
        return new AuthSuccHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/css/**", "/index", "/home", "/webjars/**").permitAll()
                .antMatchers().hasRole("USER").antMatchers("/admin/**", "/products/create", "/products/edit/**", "/products/delete/**" ).hasRole("ADMIN").and().formLogin()
                .failureUrl("/users/login?error=true").loginPage("/users/login").successHandler(authSuccHandler())
                .permitAll().and().logout().permitAll();

    }

    @Autowired
    UserAdapterService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    public StorageProperties storageProperties() {
        StorageProperties storageProperties = new StorageProperties();
        return storageProperties;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    // TODO: auslagern in eine Globale Config
    @Bean
    public CarFilterModel carFilterModel() {
        return new CarFilterModel();
    }

    @Bean
    public SearchService searchService() {
        return new SearchService();
    }

    @Bean
    public FilterService filterService() {
        return new FilterService();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}