package com.era.apiorder.model.repositories;

import com.era.apiorder.model.entities.PayType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayTypeRepository extends JpaRepository<PayType, Long> {



}
