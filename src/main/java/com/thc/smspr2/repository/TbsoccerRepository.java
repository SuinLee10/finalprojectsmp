package com.thc.smspr2.repository;

import com.thc.smspr2.domain.Tbsoccer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbsoccerRepository extends JpaRepository<Tbsoccer, String> {
}
