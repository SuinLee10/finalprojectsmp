package com.thc.smspr2.dto;

import com.thc.smspr2.domain.Tbuser;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

public class TbuserDto {

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginReqDto{
        @Schema(description = "username", example="")
        @NotNull
        @NotEmpty
        @Size(max=400)
        private String username;
        @Schema(description = "password", example="")
        @NotNull
        @NotEmpty
        @Size(max=100)
        private String password;
    }

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignupReqDto{
        @Schema(description = "username", example="")
        @NotNull
        @NotEmpty
        @Size(max=400)
        private String username;
        @Schema(description = "password", example="")
        @NotNull
        @NotEmpty
        @Size(max=100)
        private String password;
    }

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateReqDto{
        @Schema(description = "username", example="")
        @NotNull
        @NotEmpty
        @Size(max=400)
        private String username;
        @Schema(description = "password", example="")
        @NotNull
        @NotEmpty
        @Size(max=100)
        private String password;

        public Tbuser toEntity(){
            return Tbuser.of(username, password, null, null, null, null, null, null);
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
        @Schema(description = "name", example="")
        private String name;
        @Schema(description = "nick", example="")
        private String nick;
        @Schema(description = "phone", example="")
        private String phone;
        @Schema(description = "gender", example="")
        private String gender;
        @Schema(description = "content", example="")
        @Size(max=4000)
        private String content;
        @Schema(description = "img", example="")
        private String img;
    }

    //여기는 빌더 붙이면 에러 나요!! 조심!!
    @Schema
    @Getter
    @Setter
    public static class SelectResDto extends DefaultDto.SelectResDto{

        @Schema(description = "username", example="")
        private String username;
        @Schema(description = "password", example="")
        private String password;
        @Schema(description = "name", example="")
        private String name;
        @Schema(description = "nick", example="")
        private String nick;
        @Schema(description = "phone", example="")
        private String phone;
        @Schema(description = "gender", example="")
        private String gender;
        @Schema(description = "content", example="")
        private String content;
        @Schema(description = "img", example="")
        private String img;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto{
        @Schema(description = "name", example="")
        private String name;
        @Schema(description = "nick", example="")
        private String nick;
        @Schema(description = "phone", example="")
        private String phone;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto{
        @Schema(description = "name", example="")
        private String name;
        @Schema(description = "nick", example="")
        private String nick;
        @Schema(description = "phone", example="")
        private String phone;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        @Schema(description = "name", example="")
        private String name;
        @Schema(description = "nick", example="")
        private String nick;
        @Schema(description = "phone", example="")
        private String phone;
    }

}
