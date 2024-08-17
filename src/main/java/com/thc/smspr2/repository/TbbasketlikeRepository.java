package com.thc.smspr2.repository;

import com.thc.smspr2.domain.Tbbasketlike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbbasketlikeRepository extends JpaRepository<Tbbasketlike, String> {
    Tbbasketlike findByTbbasketIdAndTbuserId(String tbbasketId, String tbuserId);
}
