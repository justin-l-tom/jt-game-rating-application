package edu.pace.vgr.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;

	@Bean
    public UserDetailsService userDetailsService() {
		
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		
		users.setUsersByUsernameQuery("SELECT username, password, enabled FROM user_accounts WHERE username = ?");
        users.setAuthoritiesByUsernameQuery("SELECT username, role FROM user_accounts WHERE username = ?");
        
        return users;
        
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(
				(authz) -> authz.requestMatchers("/videogames/new", "/videogames/save")
						.hasRole("ADMIN")
						.requestMatchers("/", "/**")
						.permitAll()
						.anyRequest()
						.authenticated())
				.logout(logout -> logout.logoutUrl("/logout")
						.logoutSuccessUrl("/")
						.invalidateHttpSession(true))
				.formLogin();

		return http.build();

	}
	
}
