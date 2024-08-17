package com.thc.smspr2.repository;

import com.thc.smspr2.domain.Tbbasketfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbbasketfileRepository extends JpaRepository<Tbbasketfile, String> {
}
