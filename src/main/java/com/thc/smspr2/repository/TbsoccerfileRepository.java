package com.thc.smspr2.repository;

import com.thc.smspr2.domain.Tbsoccerfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbsoccerfileRepository extends JpaRepository<Tbsoccerfile, String> {
}
