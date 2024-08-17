package com.thc.smspr2.repository;

import com.thc.smspr2.domain.Tbmatch;
import com.thc.smspr2.domain.Tbmatchlike;
import com.thc.smspr2.domain.Tbmatchlike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbmatchlikeRepository extends JpaRepository<Tbmatchlike, String> {
    Tbmatchlike findByTbmatchIdAndTbuserId(String tbmatchId, String tbuserId);
}
