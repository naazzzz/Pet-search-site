package com.pss.PSS.repository;

import com.pss.PSS.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    UsersEntity findByUsername(String username);
}
