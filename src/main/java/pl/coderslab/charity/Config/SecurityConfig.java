package pl.coderslab.charity.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private DataSource dataSource;
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    public SecurityConfig(DataSource dataSource, AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.dataSource = dataSource;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email, password, enabled from users where email = ?")
                .authoritiesByUsernameQuery("select u.email, authority from authorities join users u on authorities.user_id = u.id where u.email = ?")
                .passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/user/**","/admin/**").authenticated()
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .and().formLogin().loginPage("/login").successHandler(authenticationSuccessHandler)
                .and().csrf().disable()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**"); // #3
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}