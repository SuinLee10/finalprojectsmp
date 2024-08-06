package com.thc.smspr2.repository;

import com.thc.smspr2.domain.Tbrefreshtoken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbrefreshtokenRepository extends JpaRepository<Tbrefreshtoken, String> {
    Tbrefreshtoken findByTbuserId(String tbuserId);
}
