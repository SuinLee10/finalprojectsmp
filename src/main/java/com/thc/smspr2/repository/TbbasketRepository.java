package com.thc.smspr2.repository;

import com.thc.smspr2.domain.Tbbasket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbbasketRepository extends JpaRepository<Tbbasket, String> {
}
