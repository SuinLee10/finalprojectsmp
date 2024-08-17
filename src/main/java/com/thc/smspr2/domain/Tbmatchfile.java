package com.thc.smspr2.domain;

import com.thc.smspr2.dto.TbmatchfileDto;
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
public class Tbmatchfile extends AuditingFields {

    @Setter @Column(nullable = false) private String tbmatchId;
    @Setter @Column(nullable = false) private String type;
    @Setter @Column(nullable = false, length=400) private String url;

    protected Tbmatchfile(){}
    private Tbmatchfile(String tbmatchId, String type, String url) {
        this.tbmatchId = tbmatchId;
        this.type = type;
        this.url = url;
    }
    public static Tbmatchfile of(String tbmatchId, String type, String url) {
        return new Tbmatchfile(tbmatchId, type, url);
    }

    public TbmatchfileDto.CreateResDto toCreateResDto() {
        return TbmatchfileDto.CreateResDto.builder().id(this.getId()).build();
    }
}
