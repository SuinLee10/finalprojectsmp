package com.thc.smspr2.repository;

import com.thc.smspr2.domain.Tbmatchfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbmatchfileRepository extends JpaRepository<Tbmatchfile, String> {
}
