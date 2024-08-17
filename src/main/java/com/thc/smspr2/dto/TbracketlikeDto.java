package com.thc.smspr2.dto;

import com.thc.smspr2.domain.Tbracketlike;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class TbracketlikeDto {

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateReqDto{
        @Schema(description = "tbracketId", example="")
        @NotNull
        @NotEmpty
        private String tbracketId;

        @Schema(description = "tbuserId", example="")
        @NotNull
        @NotEmpty
        @Size(max=100)
        private String tbuserId;

        public Tbracketlike toEntity(){
            return Tbracketlike.of(tbracketId, tbuserId);
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
        @Schema(description = "tbracketId", example="")
        private String tbracketId;

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
        @Schema(description = "tbracketId", example="")
        private String tbracketId;

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
        @Schema(description = "tbracketId", example="")
        private String tbracketId;
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
        @Schema(description = "tbracketId", example="")
        private String tbracketId;
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
        @Schema(description = "tbracketId", example="")
        private String tbracketId;
        @Schema(description = "tbuserId", example="")
        private String tbuserId;
    }

}
