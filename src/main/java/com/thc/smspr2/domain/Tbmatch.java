package com.thc.smspr2.domain;

import com.thc.smspr2.dto.TbmatchDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Table(indexes = {
        @Index(columnList = "deleted")
        ,@Index(columnList = "process")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
})
@Entity
public class Tbmatch extends AuditingFields {

    @Setter @Column(nullable = false) private String tbuserId; //(fk이지만, 따로 설정은 안함.)
    @Setter @Column(nullable = false, length=400) private String title; // 예: 손양원 축구할 사람 모집
    @Setter @Column(nullable = false) private Integer countread; // 조회수

    @Setter @Column(nullable = false) private Integer minParticipants; // 최소 참여 인원
    @Setter @Column(nullable = false) private Integer maxParticipants; // 최대 참여 인원
    @Setter @Column(nullable = false) private Integer femaleParticipants; // 여자 참여 인원
    @Setter @Column(nullable = false) private Integer maleParticipants; // 남자 참여 인원
    @Setter @Column(nullable = false) private String minSchoolNum; // 최소 학번
    @Setter @Column(nullable = false) private String maxSchoolNum; // 최대 학번
    @Setter @Column(nullable = false) private String place; // 장소

    @Setter @Column(nullable = false) private LocalDate matchDate; // 경기 날짜
    @Setter @Column(nullable = false) private LocalDateTime startTime; // 시작 시간
    @Setter @Column(nullable = false) private LocalDateTime endTime; // 종료 시간

    @Setter @Column(nullable = true, length=10000) @Lob private String content; // 전달사항
    protected Tbmatch(){}
    private Tbmatch(String tbuserId, String title, Integer countread, Integer minParticipants, Integer maxParticipants, Integer femaleParticipants, Integer maleParticipants, String minSchoolNum, String maxSchoolNum, String place, LocalDate matchDate, LocalDateTime startTime, LocalDateTime endTime , String content) {
        this.tbuserId = tbuserId;
        this.title = title;
        this.countread = countread;
        this.minParticipants = minParticipants;
        this.maxParticipants = maxParticipants;
        this.femaleParticipants = femaleParticipants;
        this.maleParticipants = maleParticipants;
        this.minSchoolNum = minSchoolNum;
        this.maxSchoolNum = maxSchoolNum;
        this.place = place;
        this.matchDate = matchDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.content = content;

    }
    public static Tbmatch of(String tbuserId, String title, Integer minParticipants, Integer maxParticipants, Integer femaleParticipants, Integer maleParticipants, String minSchoolNum, String maxSchoolNum, String place, LocalDate matchDate, LocalDateTime startTime, LocalDateTime endTime , String content) {
        return new Tbmatch(tbuserId, title, 0, minParticipants, maxParticipants, femaleParticipants, maleParticipants, minSchoolNum, maxSchoolNum, place, matchDate, startTime, endTime, content);
    }

    public TbmatchDto.CreateResDto toCreateResDto() {
        return TbmatchDto.CreateResDto.builder().id(this.getId()).build();
    }
}
