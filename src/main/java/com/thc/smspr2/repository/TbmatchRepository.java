package com.thc.smspr2.repository;

import com.thc.smspr2.domain.Tbmatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbmatchRepository extends JpaRepository<Tbmatch, String> {
}
