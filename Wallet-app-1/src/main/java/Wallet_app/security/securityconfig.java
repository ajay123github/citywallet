package Wallet_app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import Wallet_app.service.UserInfoDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class securityconfig {

	@Autowired
	filter jwtfilter;
	
	@Bean
	public UserDetailsService userDetailsService() {
//		UserDetails admin=User.withUsername("RAM")
//				.password(encoder.encode("R1"))
//				.roles("ADMIN")
//				.build();
//		
//		UserDetails user=User.withUsername("LUCKY")
//				.password(encoder.encode("L1"))
//				.roles("USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(admin,user);
		return new UserInfoDetailsService();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		 httpSecurity.csrf().disable()
		.authorizeHttpRequests(auth->{
			try {
				auth.requestMatchers("/wallet/login","wallet/set/userinfo","/wallet/token").permitAll()
//				.requestMatchers("/wallet/gettransactions").hasRole("ADMIN")
//				.requestMatchers("/wallet/transactions/analyse").hasAnyRole("ADMIN","USER")
						.requestMatchers("/wallet/**")
						.authenticated().and().sessionManagement()
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authenticationProvider(authenticationProvider())
						.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				).httpBasic();
		return httpSecurity.build();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean  
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
		
	}
}
