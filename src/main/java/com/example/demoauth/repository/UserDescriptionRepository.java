package com.example.demoauth.repository;


import com.example.demoauth.models.UserDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDescriptionRepository extends JpaRepository<UserDescriptionEntity, Long> {
    UserDescriptionEntity findByEmail(String email);
    UserDescriptionEntity save(UserDescriptionEntity entity);

    boolean existsByEmail(String email);


}
