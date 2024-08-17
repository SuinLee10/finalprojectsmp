package com.thc.smspr2.domain;

import com.thc.smspr2.dto.TbbasketfileDto;
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
public class Tbbasketfile extends AuditingFields {

    @Setter @Column(nullable = false) private String tbbasketId;
    @Setter @Column(nullable = false) private String type;
    @Setter @Column(nullable = false, length=400) private String url;

    protected Tbbasketfile(){}
    private Tbbasketfile(String tbbasketId, String type, String url) {
        this.tbbasketId = tbbasketId;
        this.type = type;
        this.url = url;
    }
    public static Tbbasketfile of(String tbbasketId, String type, String url) {
        return new Tbbasketfile(tbbasketId, type, url);
    }

    public TbbasketfileDto.CreateResDto toCreateResDto() {
        return TbbasketfileDto.CreateResDto.builder().id(this.getId()).build();
    }
}
