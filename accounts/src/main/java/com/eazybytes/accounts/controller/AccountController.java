package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constant.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.dto.ReponseDto;
import com.eazybytes.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/***
 * @AllArgsConstructor its a part of lombok but we are using here for Constructor dependencies
 * @Validated its for we added validation on request DTO with @Valid
 * @Operation and @Tag and @ApiResponse for swagger
 * @Schema
 */


@RestController
@RequestMapping(path = "/api/v1/", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
@Tag(
        name=" API for Account",
        description = "Its provide the CRUD functionality"
)
public class AccountController {

    IAccountService iAccountService;

    @GetMapping("hello")
    @Operation(
            summary="Checking for server UP",
            description = "REST APT for server ON"
    )
    public String hello(){
        return "hi Amol";
    }

    @Operation(
            summary = "Create account REST API",
            description = "REST API to create new Customer and Account"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status CREATED"
    )
    @PostMapping("createAcc")
    public ResponseEntity<ReponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ReponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetching REST API",
            description = "REST API to  get Customer and Account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status Internal server error",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    })
    @GetMapping("featchAcc")
    public ResponseEntity<CustomerDto> featchAccountDetails(@RequestParam
                                                                @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
                                                                String mobileNo){
        CustomerDto customerDto = iAccountService.featchAccountDetails(mobileNo);

        return new ResponseEntity<>(customerDto,HttpStatus.OK);
    }

    @Operation(
            summary = "For updating customer information",
            description = "REST API for update existing account"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP status  OK"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP status  Internal server error",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorResponseDto.class
                                    )
                            )
                    )
            }
    )
    @PutMapping("updateAcc")
    public ResponseEntity<ReponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountService.updateAccount(customerDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ReponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ReponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("deleteAcc")
    @Operation(
            summary = "Delete account api",
            description = "REST API for to delete existing account"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
    public ResponseEntity<ReponseDto> deleteAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
                                                               String mobileNo){
        boolean isDeleted = iAccountService.deleteAccount(mobileNo);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ReponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ReponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }


}
