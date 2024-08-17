package com.thc.smspr2.domain;

import com.thc.smspr2.dto.TbracketfileDto;
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
public class Tbracketfile extends AuditingFields {

    @Setter @Column(nullable = false) private String tbracketId;
    @Setter @Column(nullable = false) private String type;
    @Setter @Column(nullable = false, length=400) private String url;

    protected Tbracketfile(){}
    private Tbracketfile(String tbracketId, String type, String url) {
        this.tbracketId = tbracketId;
        this.type = type;
        this.url = url;
    }
    public static Tbracketfile of(String tbracketId, String type, String url) {
        return new Tbracketfile(tbracketId, type, url);
    }

    public TbracketfileDto.CreateResDto toCreateResDto() {
        return TbracketfileDto.CreateResDto.builder().id(this.getId()).build();
    }
}
