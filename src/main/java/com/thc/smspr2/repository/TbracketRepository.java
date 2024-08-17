package com.thc.smspr2.repository;

import com.thc.smspr2.domain.Tbracket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbracketRepository extends JpaRepository<Tbracket, String> {
}
