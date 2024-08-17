package com.thc.smspr2.dto;

import com.thc.smspr2.domain.Tbbasketlike;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class TbbasketlikeDto {

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateReqDto{
        @Schema(description = "tbbasketId", example="")
        @NotNull
        @NotEmpty
        private String tbbasketId;

        @Schema(description = "tbuserId", example="")
        @NotNull
        @NotEmpty
        @Size(max=100)
        private String tbuserId;

        public Tbbasketlike toEntity(){
            return Tbbasketlike.of(tbbasketId, tbuserId);
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
        @Schema(description = "tbbasketId", example="")
        private String tbbasketId;

        @Schema(description = "tbuserId", example="")
        @Size(max=100)
        private String tbuserId;
        @Schema(description = "content", example="")
        @Size(max=400)
        private String content;
    }

    //여기는 빌더 붙이면 에러 나요!! 조심!!
    @Schema
    @Getter
    @Setter
    public static class DetailResDto extends DefaultDto.DetailResDto{
        @Schema(description = "tbbasketId", example="")
        private String tbbasketId;

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
        @Schema(description = "tbbasketId", example="")
        private String tbbasketId;
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
        @Schema(description = "tbbasketId", example="")
        private String tbbasketId;
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
        @Schema(description = "tbbasketId", example="")
        private String tbbasketId;
        @Schema(description = "tbuserId", example="")
        private String tbuserId;
    }

}
