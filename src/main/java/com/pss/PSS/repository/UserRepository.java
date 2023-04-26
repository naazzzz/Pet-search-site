package com.pss.PSS.repository;

import java.util.List;
import java.util.Optional;

import com.pss.PSS.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
	User getUserByUsername(String username);
	User findByUsernameAndPassword(String username, String password);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	User save(User entity);


}
