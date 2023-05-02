package com.pss.PSS.repository;

import com.pss.PSS.models.AdEntity;

import com.pss.PSS.models.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<AdEntity, Long> {

    AdEntity save(AdEntity entity);

    List<AdEntity> getAllByStatus(Status status);
    AdEntity findById(int id);

    List<AdEntity> getAllByIdIsNotNull();

    void deleteById(int id);

    List<AdEntity> getAllByUserId(Long id);

    List<AdEntity> getTopByDate(String date);
}
