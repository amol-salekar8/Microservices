package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(name ="ErrorResponse", description = "Error captures and show")
public class ErrorResponseDto {

    @Schema(description = "API path invoked by client" )
    private String apiPath;

    @Schema(description = "Error code representing error happened" )
    private HttpStatus errorCode;

    @Schema(description = "Error message representing error happened" )
    private String errorMsg;

    @Schema(description = "Error time representing error happened" )
    private LocalDateTime errorTime;
}
