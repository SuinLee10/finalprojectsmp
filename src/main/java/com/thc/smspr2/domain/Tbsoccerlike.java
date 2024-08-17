package com.thc.smspr2.domain;

import com.thc.smspr2.dto.TbsoccerlikeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Table(indexes = {
        @Index(columnList = "deleted")
        ,@Index(columnList = "process")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
        }
        ,uniqueConstraints = {
        @UniqueConstraint(
        name = "UQ_tbsoccerlike_tbsoccer_id_tbuser_id"
        ,columnNames = {"tbsoccerId", "tbuserId"}
        )}
)
@Entity
public class Tbsoccerlike extends AuditingFields {

    @Setter @Column(nullable = false) private String tbsoccerId;
    @Setter @Column(nullable = false) private String tbuserId;

    protected Tbsoccerlike(){}
    private Tbsoccerlike(String tbsoccerId, String tbuserId) {
        this.tbsoccerId = tbsoccerId;
        this.tbuserId = tbuserId;
    }
    public static Tbsoccerlike of(String tbsoccerId, String tbuserId) {
        return new Tbsoccerlike(tbsoccerId, tbuserId);
    }

    public TbsoccerlikeDto.CreateResDto toCreateResDto() {
        return TbsoccerlikeDto.CreateResDto.builder().id(this.getId()).build();
    }
}
