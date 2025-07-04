package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/***
 * Adding @NotEmpty, @Size and @Pattern are the part of spring validation
 * with there properties while checking request
 */

@Data
@Schema(name = "Customer")
public class CustomerDto {

    @NotEmpty(message = "name cannot be null or empty")
    @Size(min = 5, max = 30, message = "The length of customer name is between 5 to 30")
    @Schema(
            description = "Customer name", example = "Jony deep"
    )
    private String name;

    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email address should be in valid format")
    @Schema(
            description = "Customer Email ID", example = "jonydeep@fed.com"
    )
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
    @Schema(
            description = "Customer mobile", example = "7896541230"
    )
    private String mobileNo;

    private AccountsDto accountsDto;
}
