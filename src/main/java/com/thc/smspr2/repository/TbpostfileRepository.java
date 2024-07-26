package com.thc.smspr2.repository;

import com.thc.smspr2.domain.Tbpostfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbpostfileRepository extends JpaRepository<Tbpostfile, String> {
}
