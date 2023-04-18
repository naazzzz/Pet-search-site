package com.pss.PSS.service;

import com.pss.PSS.models.UserDescriptionEntity;
import com.pss.PSS.repository.UserDescriptionRepository;
import com.pss.PSS.models.User;
import com.pss.PSS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public User getUser(String login){
		return userRepository.getUserByUsername(login);
	}

	public UserDescriptionEntity saveUserDesc(UserDescriptionEntity entity){

		if(repository_desc.existsByEmail(entity.getEmail())){
			return repository_desc.findByEmail(entity.getEmail());
		}


		return repository_desc.save(entity);
	}
}
