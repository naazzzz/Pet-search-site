package com.example.demoauth.repository;

import com.example.demoauth.models.AdEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository<AdEntity, Long> {

    AdEntity save(AdEntity entity);

    AdEntity findById(int id);
}
