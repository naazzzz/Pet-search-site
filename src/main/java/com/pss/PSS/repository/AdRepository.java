package com.pss.PSS.repository;

import com.pss.PSS.model.AdEntity;
import com.pss.PSS.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository<AdEntity, Long> {

    AdEntity save(AdEntity entity);

    AdEntity findById(int id);
}
