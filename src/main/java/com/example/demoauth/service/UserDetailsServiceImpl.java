package com.example.demoauth.service;

import com.example.demoauth.models.UserDescriptionEntity;
import com.example.demoauth.repository.UserDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoauth.models.User;
import com.example.demoauth.repository.UserRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDescriptionRepository repository_desc;


	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return UserDetailsImpl.build(user);
	}

	public ResponseEntity<Optional<User>> getUser(String login){
		Optional<User> user = userRepository.findByUsername(login);
		if(user.isEmpty()){
			return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(user);
	}

	public UserDescriptionEntity saveUserDesc(UserDescriptionEntity entity){

		if(repository_desc.existsByEmail(entity.getEmail())){
			return repository_desc.findByEmail(entity.getEmail());
		}


		return repository_desc.save(entity);
	}
}
