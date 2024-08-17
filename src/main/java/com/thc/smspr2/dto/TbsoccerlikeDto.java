package com.thc.smspr2.dto;

import com.thc.smspr2.domain.Tbsoccerlike;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class TbsoccerlikeDto {

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateReqDto{
        @Schema(description = "tbsoccerId", example="")
        @NotNull
        @NotEmpty
        private String tbsoccerId;

        @Schema(description = "tbuserId", example="")
        @NotNull
        @NotEmpty
        @Size(max=100)
        private String tbuserId;

        public Tbsoccerlike toEntity(){
            return Tbsoccerlike.of(tbsoccerId, tbuserId);
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
    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        @Schema(description = "tbsoccerId", example="")
        private String tbsoccerId;

        @Schema(description = "tbuserId", example="")
        @Size(max=100)
        private String tbuserId;
        @Schema(description = "content", example="")
        @Size(max=400)
        private String content;
    }

    @Schema
    @Getter
    @Setter
    public static class DetailResDto extends DefaultDto.DetailResDto{
        @Schema(description = "tbsoccerId", example="")
        private String tbsoccerId;

        @Schema(description = "tbuserId", example="")
        private String tbuserId;
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
        @Schema(description = "tbsoccerId", example="")
        private String tbsoccerId;
        @Schema(description = "tbuserId", example="")
        private String tbuserId;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto{
        @Schema(description = "tbsoccerId", example="")
        private String tbsoccerId;
        @Schema(description = "tbuserId", example="")
        private String tbuserId;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        @Schema(description = "tbsoccerId", example="")
        private String tbsoccerId;
        @Schema(description = "tbuserId", example="")
        private String tbuserId;
    }

}
