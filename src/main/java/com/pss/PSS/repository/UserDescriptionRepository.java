package com.pss.PSS.repository;

import com.pss.PSS.model.UserDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDescriptionRepository extends JpaRepository<UserDescriptionEntity, Long> {
    UserDescriptionEntity findByEmail(String email);
    UserDescriptionEntity save(UserDescriptionEntity entity);

    boolean existsByEmail(String email);


}
