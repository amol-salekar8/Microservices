package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema (
        name = "Accounts",
        description = "For accounts information"
)
public class AccountsDto {
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digit")
    @NotEmpty(message = "Account number cannot be null or empty")
    @Schema(
            description = "field for account number" ,example = "7894563210"
    )
    private Long accountNumber;

    @NotEmpty(message = "Account Type cannot be null or empty")
    @Schema(
            description = "define type of account", example = "saving"
    )
    private String accountType;

    @NotEmpty(message = "Branch address cannot be null or empty")
    @Schema(
            description = "Location of branch", example = "Mumbai"
    )
    private String branchAddress;

}
