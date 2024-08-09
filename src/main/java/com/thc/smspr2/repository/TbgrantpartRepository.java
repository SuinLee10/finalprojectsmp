package com.thc.smspr2.repository;

import com.thc.smspr2.domain.Tbgrantpart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbgrantpartRepository extends JpaRepository<Tbgrantpart, String> {
    Tbgrantpart findByTbgrantIdAndCateAndContent(String tbgrantId, String cate, String content);
}
