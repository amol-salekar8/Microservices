package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema (
        name ="Response of API",
        description = "REST API response type"
)
public class ReponseDto {

    @Schema(description = "Response of REST API code")
    private String statusCode;

    @Schema(description = "Response of REST API message")
    private String statusMsg;
}
