package com.thc.smspr2.repository;

import com.thc.smspr2.domain.Tbracketlike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbracketlikeRepository extends JpaRepository<Tbracketlike, String> {
    Tbracketlike findByTbracketIdAndTbuserId(String tbracketId, String tbuserId);
}
