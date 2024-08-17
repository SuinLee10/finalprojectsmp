package com.thc.smspr2.dto;

import com.thc.smspr2.domain.Tbmatchlike;
import com.thc.smspr2.domain.Tbpostlike;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class TbmatchlikeDto {

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateReqDto{
        @Schema(description = "tbmatchId", example="")
        @NotNull
        @NotEmpty
        private String tbmatchId;

        @Schema(description = "tbuserId", example="")
        @NotNull
        @NotEmpty
        @Size(max=100)
        private String tbuserId;

        public Tbmatchlike toEntity(){
            return Tbmatchlike.of(tbmatchId, tbuserId);
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
        @Schema(description = "tbmatchId", example="")
        private String tbmatchId;

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
        @Schema(description = "tbmatchId", example="")
        private String tbmatchId;

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
        @Schema(description = "tbmatchId", example="")
        private String tbmatchId;
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
        @Schema(description = "tbmatchId", example="")
        private String tbmatchId;
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
        @Schema(description = "tbmatchId", example="")
        private String tbmatchId;
        @Schema(description = "tbuserId", example="")
        private String tbuserId;
    }

}
