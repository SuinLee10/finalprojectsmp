package com.thc.smspr2.domain;

import com.thc.smspr2.dto.TbmatchlikeDto;
import com.thc.smspr2.dto.TbpostlikeDto;
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
        name = "UQ_tbmatchlike_tbmatch_id_tbuser_id"
        ,columnNames = {"tbmatchId", "tbuserId"}
        )}
)
@Entity
public class Tbmatchlike extends AuditingFields {

    @Setter @Column(nullable = false) private String tbmatchId;
    @Setter @Column(nullable = false) private String tbuserId;

    protected Tbmatchlike(){}
    private Tbmatchlike(String tbmatchId, String tbuserId) {
        this.tbmatchId = tbmatchId;
        this.tbuserId = tbuserId;
    }
    public static Tbmatchlike of(String tbmatchId, String tbuserId) {
        return new Tbmatchlike(tbmatchId, tbuserId);
    }

    public TbmatchlikeDto.CreateResDto toCreateResDto() {
        return TbmatchlikeDto.CreateResDto.builder().id(this.getId()).build();
    }
}
