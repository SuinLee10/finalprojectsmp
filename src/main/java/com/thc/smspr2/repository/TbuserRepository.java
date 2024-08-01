package com.thc.smspr2.repository;

import com.thc.smspr2.domain.Tbuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TbuserRepository extends JpaRepository<Tbuser, String> {
    Tbuser findByUsername(String username);
    Tbuser findByUsernameAndPassword(String username, String password);
}
