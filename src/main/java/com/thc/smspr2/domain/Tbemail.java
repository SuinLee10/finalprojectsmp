package com.thc.smspr2.domain;

import com.thc.smspr2.dto.TbemailDto;
import jakarta.persistence.*;
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
public class Tbemail extends AuditingFields {

    @Setter @Column(nullable = false, unique = true) private String username;
    @Setter @Column(nullable = false) private String number;

    protected Tbemail(){}
    private Tbemail(String username, String number) {
        this.username = username;
        this.number = number;
    }
    public static Tbemail of(String username, String number) {
        return new Tbemail(username, number);
    }

    public TbemailDto.CreateResDto toCreateResDto() {
        return TbemailDto.CreateResDto.builder().id(this.getId()).build();
    }
}
