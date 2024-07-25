package com.thc.smspr2.domain;

import com.thc.smspr2.dto.TbboardDto;
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
public class Tbboard extends AuditingFields {

    @Setter @Column(nullable = false, length=400) private String title;
    @Setter @Column(nullable = false, length=400) private String author;
    @Setter @Column(nullable = true, length=10000) @Lob private String content; // 본문

    protected Tbboard(){}
    private Tbboard(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }
    public static Tbboard of(String title, String author, String content) {
        return new Tbboard(title, author, content);
    }

    public TbboardDto.CreateResDto toCreateResDto() {
        //생성자로 리턴해도 되지만!1
        /*
        TbboardDto.CreateResDto createResDto1 = new TbboardDto.CreateResDto();
        createResDto1.setId(this.getId());
        */
        //빌더를 쓰면 매우 깔끔!!
        return TbboardDto.CreateResDto.builder().id(this.getId()).build();
    }
}
