package com.thc.smspr2.domain;

import com.thc.smspr2.dto.TbpostDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Table(indexes = {
        @Index(columnList = "deleted")
        ,@Index(columnList = "process")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
}) //인덱스 키가 유니크 키(여러개를 한번에 설정할때) 등을 설정할 수 있습니다.
@Entity
public class Tbpost extends AuditingFields {

    //원래는 여기에 이렇게 선언합니다.
    //하지만 공통으로 쓰는 필드들이 있으니, 상속을 해보겠습니다!!
    /*@Id private String id;
    @Setter private String deleted;
    @Setter private String createdAt;
    @Setter private String modifiedAt;*/

    @Setter @Column(nullable = false, length=400) private String title;
    @Setter @Column(nullable = false, length=400) private String author;
    @Setter @Column(nullable = true, length=10000) @Lob private String content; // 본문

    protected Tbpost(){}
    private Tbpost(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }
    public static Tbpost of(String title, String author, String content) {
        return new Tbpost(title, author, content);
    }

    public TbpostDto.CreateResDto toCreateResDto() {
        //생성자로 리턴해도 되지만!1
        TbpostDto.CreateResDto createResDto1 = new TbpostDto.CreateResDto();
        createResDto1.setId(this.getId());
        //빌더를 쓰면 매우 깔끔!!
        return TbpostDto.CreateResDto.builder().id(this.getId()).build();
    }
}
