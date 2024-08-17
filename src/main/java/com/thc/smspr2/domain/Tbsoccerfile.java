package com.thc.smspr2.domain;

import com.thc.smspr2.dto.TbsoccerfileDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Table(indexes = {
        @Index(columnList = "deleted")
        ,@Index(columnList = "process")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
})
@Entity
public class Tbsoccerfile extends AuditingFields {

    @Setter @Column(nullable = false) private String tbsoccerId;
    @Setter @Column(nullable = false) private String type;
    @Setter @Column(nullable = false, length=400) private String url;

    protected Tbsoccerfile(){}
    private Tbsoccerfile(String tbsoccerId, String type, String url) {
        this.tbsoccerId = tbsoccerId;
        this.type = type;
        this.url = url;
    }
    public static Tbsoccerfile of(String tbsoccerId, String type, String url) {
        return new Tbsoccerfile(tbsoccerId, type, url);
    }

    public TbsoccerfileDto.CreateResDto toCreateResDto() {
        return TbsoccerfileDto.CreateResDto.builder().id(this.getId()).build();
    }
}
