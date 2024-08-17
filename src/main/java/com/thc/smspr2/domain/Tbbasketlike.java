package com.thc.smspr2.domain;

import com.thc.smspr2.dto.TbbasketlikeDto;
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
        name = "UQ_tbbasketlike_tbbasket_id_tbuser_id"
        ,columnNames = {"tbbasketId", "tbuserId"}
        )}
)
@Entity
public class Tbbasketlike extends AuditingFields {

    @Setter @Column(nullable = false) private String tbbasketId;
    @Setter @Column(nullable = false) private String tbuserId;

    protected Tbbasketlike(){}
    private Tbbasketlike(String tbbasketId, String tbuserId) {
        this.tbbasketId = tbbasketId;
        this.tbuserId = tbuserId;
    }
    public static Tbbasketlike of(String tbbasketId, String tbuserId) {
        return new Tbbasketlike(tbbasketId, tbuserId);
    }

    public TbbasketlikeDto.CreateResDto toCreateResDto() {
        return TbbasketlikeDto.CreateResDto.builder().id(this.getId()).build();
    }
}
