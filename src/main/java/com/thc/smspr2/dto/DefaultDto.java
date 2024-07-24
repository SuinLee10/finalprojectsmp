package com.thc.smspr2.dto;

import com.thc.smspr2.domain.Tbboard;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

public class DefaultDto {
    @Builder
    @Schema
    @Getter
    @Setter
    public static class FileResDto{
        private String url;
    }
}
