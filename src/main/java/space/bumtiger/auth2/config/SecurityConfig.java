package space.bumtiger.auth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import space.bumtiger.auth2.repository.UserRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	// @formatter:off
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(
			HttpSecurity http) throws Exception {
		http.headers((headers) -> headers.frameOptions(frm -> frm.disable()));
		return http.authorizeHttpRequests(
						requests -> {
							requests.requestMatchers("/h2-console/**").permitAll();
							requests.anyRequest().authenticated();
						}
						).csrf(csrf -> {
							try {
								csrf.ignoringRequestMatchers("/h2-console/**")
								      .disable()
								        .httpBasic(HttpBasicConfigurer::disable);
							} catch (Exception e) {
								e.printStackTrace();
							}
						})
				.formLogin(Customizer.withDefaults()).build();
	}
	// @formatter:on

	@Bean
	UserDetailsService userDetailsService(UserRepository userRepo) {
		return username -> userRepo.findByUsername(username);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
