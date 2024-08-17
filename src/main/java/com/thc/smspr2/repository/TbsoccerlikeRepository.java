package com.thc.smspr2.repository;

import com.thc.smspr2.domain.Tbsoccerlike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbsoccerlikeRepository extends JpaRepository<Tbsoccerlike, String> {
    Tbsoccerlike findByTbsoccerIdAndTbuserId(String tbsoccerId, String tbuserId);
}
