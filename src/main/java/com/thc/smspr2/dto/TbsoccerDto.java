package com.thc.smspr2.dto;

import com.thc.smspr2.domain.Tbsoccer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TbsoccerDto {

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateReqDto extends DefaultDto.BaseDto {
        @Schema(description = "title", example="")
        @NotNull
        @NotEmpty
        @Size(max=400)
        private String title;

        @Schema(description = "content", example="")
        @Size(max=4000)
        private String content;

        @Schema(description = "minParticipants", example="7")
        @NotNull
        private Integer minParticipants;

        @Schema(description = "maxParticipants", example="12")
        @NotNull
        private Integer maxParticipants;

        @Schema(description = "femaleParticipants", example="7")
        @NotNull
        private Integer femaleParticipants;

        @Schema(description = "maleParticipants", example="5")
        @NotNull
        private Integer maleParticipants;

        @Schema(description = "minSchoolNum", example="17")
        @NotNull
        @Size(max=50)
        private String minSchoolNum;

        @Schema(description = "maxSchoolNum", example="21")
        @NotNull
        @Size(max=50)
        private String maxSchoolNum;

        @Schema(description = "place", example="평봉필드")
        @NotNull
        @Size(max=400)
        private String place;

        @Schema(description = "matchDate", example="2024-08-14")
        @NotNull
        private LocalDate matchDate;

        @Schema(description = "startTime", example="09:00")
        @NotNull
        private LocalDateTime startTime;

        @Schema(description = "endTime", example="11:00")
        @NotNull
        private LocalDateTime endTime;

        @Schema(description = "tbsoccerfileTypes", example="")
        private List<String> tbsoccerfileTypes;
        @Schema(description = "tbsoccerfileUrls", example="")
        private List<String> tbsoccerfileUrls;
    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateServDto extends CreateReqDto{
        private String reqTbuserId;
        private boolean isAdmin;

        private String tbuserId;

        public Tbsoccer toEntity(){
            return Tbsoccer.of(getTbuserId(), getTitle(), getMinParticipants(),getMaxParticipants(),getFemaleParticipants(),getMaleParticipants(),getMinSchoolNum(),getMaxSchoolNum(),getPlace(),getMatchDate(),getStartTime(),getEndTime(),getContent());
        }
    }
    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateResDto{
        private String id;
        private String title;
        private LocalDate matchDate;
    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        @Schema(description = "title", example="")
        @Size(max=400)
        private String title;

        @Schema(description = "content", example="")
        @Size(max=4000)
        private String content;

        @Schema(description = "minParticipants", example="")
        private Integer minParticipants;

        @Schema(description = "maxParticipants", example="")
        private Integer maxParticipants;

        @Schema(description = "femaleParticipants", example="")
        private Integer femaleParticipants;

        @Schema(description = "maleParticipants", example="")
        private Integer maleParticipants;

        @Schema(description = "minSchoolNum", example="")
        @Size(max=50)
        private String minSchoolNum;

        @Schema(description = "maxSchoolNum", example="")
        @Size(max=50)
        private String maxSchoolNum;

        @Schema(description = "place", example="")
        @Size(max=400)
        private String place;

        @Schema(description = "matchDate", example="2024-08-14")
        private LocalDate matchDate;

        @Schema(description = "startTime", example="09:00")
        private LocalDateTime startTime;

        @Schema(description = "endTime", example="11:00")
        private LocalDateTime endTime;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateServDto extends UpdateReqDto{
        private String reqTbuserId;
        private boolean isAdmin;

        private Integer countread;
    }

    //여기는 빌더 붙이면 에러 나요!! 조심!!
    @Schema
    @Getter
    @Setter
    public static class DetailResDto extends DefaultDto.DetailResDto{
        @Schema(description = "tbuserId", example="")
        private String tbuserId;

        @Schema(description = "title", example="")
        private String title;

        @Schema(description = "tbuserName", example="")
        private String tbuserName;
        @Schema(description = "tbuserNick", example="")
        private String tbuserNick;
        @Schema(description = "tbuserImg", example="")
        private String tbuserImg;

        @Schema(description = "tbsoccerfiles", example="")
        private List<TbsoccerfileDto.DetailResDto> tbsoccerfiles;

        @Schema(description = "countread", example="")
        private Integer countread;
        @Schema(description = "liked", example="")
        private Boolean liked;

        @Schema(description = "minParticipants", example="")
        private Integer minParticipants;

        @Schema(description = "maxParticipants", example="")
        private Integer maxParticipants;

        @Schema(description = "femaleParticipants", example="")
        private Integer femaleParticipants;

        @Schema(description = "maleParticipants", example="")
        private Integer maleParticipants;

        @Schema(description = "minSchoolNum", example="")
        private String minSchoolNum;

        @Schema(description = "maxSchoolNum", example="")
        private String maxSchoolNum;

        @Schema(description = "place", example="")
        private String place;

        @Schema(description = "matchDate", example="2024-08-14")
        private LocalDate matchDate;

        @Schema(description = "startTime", example="09:00")
        private LocalDateTime startTime;

        @Schema(description = "endTime", example="11:00")
        private LocalDateTime endTime;

        @Schema(description = "content", example="")
        private String content;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto{
        @Schema(description = "tbuserId", example="")
        private String tbuserId;
        @Schema(description = "title", example="")
        private String title;
    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListServDto extends ListReqDto{
        private String reqTbuserId;
        private boolean isAdmin;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto{
        @Schema(description = "tbuserId", example="")
        private String tbuserId;
        @Schema(description = "title", example="")
        private String title;
    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PagedListServDto extends DefaultDto.PagedListServDto{
        private String reqTbuserId;
        private boolean isAdmin;

        @Schema(description = "tbuserId", example="")
        private String tbuserId;
        @Schema(description = "title", example="")
        private String title;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        @Schema(description = "tbuserId", example="")
        private String tbuserId;
        @Schema(description = "title", example="")
        private String title;
    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScrollListServDto extends ScrollListReqDto{
        private String reqTbuserId;
        private boolean isAdmin;
    }

}
