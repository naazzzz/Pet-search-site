package com.pss.PSS.service;

import com.pss.PSS.model.RoleEntity;
import com.pss.PSS.model.UsersEntity;
import com.pss.PSS.repository.RoleRepository;
import com.pss.PSS.repository.UserRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public UsersEntity findUserById(Long userId) {
        Optional<UsersEntity> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new UsersEntity());
    }

    public List<UsersEntity> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(UsersEntity user) {
        UsersEntity userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new RoleEntity(1, "ROLE_USER")));
        user.setPass(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<UsersEntity> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM UsersEntity u WHERE u.id > :paramId", UsersEntity.class)
                .setParameter("paramId", idMin).getResultList();
    }
}
