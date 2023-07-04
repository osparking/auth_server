package space.bumtiger.auth2.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import space.bumtiger.auth2.domain.User;
import space.bumtiger.auth2.repository.UserRepository;


@Configuration
public class ApplConfig {

	private void createUserIfNotExists(UserRepository repository,
			PasswordEncoder encoder, String username, String roles) {
		if (null == repository.findByUsername(username)) {
			repository.save(new User(username, encoder.encode("1234"), roles));
		}
	}

	@Bean
	ApplicationRunner dataLoader(UserRepository repository,
			PasswordEncoder encoder) {
		return args -> {
			createUserIfNotExists(repository, encoder, "soap",
					"ROLE_ADMIN,ROLE_USER");
			createUserIfNotExists(repository, encoder, "bum", "ROLE_USER");
		};
	}
	
}