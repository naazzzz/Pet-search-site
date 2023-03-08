package com.pss.PSS.repository;

import com.pss.PSS.model.UsersEntity;
import com.pss.PSS.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    UsersEntity findByUsername(String username);
    UsersEntity save(UsersEntity entity);
}
