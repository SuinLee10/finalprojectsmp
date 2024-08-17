package com.thc.smspr2.domain;

import com.thc.smspr2.dto.TbracketlikeDto;
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
        name = "UQ_tbracketlike_tbracket_id_tbuser_id"
        ,columnNames = {"tbracketId", "tbuserId"}
        )}
)
@Entity
public class Tbracketlike extends AuditingFields {

    @Setter @Column(nullable = false) private String tbracketId;
    @Setter @Column(nullable = false) private String tbuserId;

    protected Tbracketlike(){}
    private Tbracketlike(String tbracketId, String tbuserId) {
        this.tbracketId = tbracketId;
        this.tbuserId = tbuserId;
    }
    public static Tbracketlike of(String tbracketId, String tbuserId) {
        return new Tbracketlike(tbracketId, tbuserId);
    }

    public TbracketlikeDto.CreateResDto toCreateResDto() {
        return TbracketlikeDto.CreateResDto.builder().id(this.getId()).build();
    }
}
