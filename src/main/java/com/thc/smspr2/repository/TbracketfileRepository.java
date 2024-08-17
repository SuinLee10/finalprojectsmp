package com.thc.smspr2.repository;

import com.thc.smspr2.domain.Tbracketfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbracketfileRepository extends JpaRepository<Tbracketfile, String> {
}
