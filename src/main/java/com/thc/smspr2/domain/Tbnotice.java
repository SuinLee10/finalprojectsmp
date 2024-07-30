package com.thc.smspr2.domain;

import com.thc.smspr2.dto.TbpostDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Tbnotice{
    @Id @Setter @Column(nullable = false) private String id; //PK!!!!
    @Setter @Column(nullable = false, length=400) private String title;
    @Setter @Column(nullable = true, length=10000) @Lob private String content; // 본문
}
