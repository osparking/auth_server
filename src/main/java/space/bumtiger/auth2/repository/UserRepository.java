package space.bumtiger.auth2.repository;

import org.springframework.data.repository.CrudRepository;

import space.bumtiger.auth2.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUsername(String username);

}
