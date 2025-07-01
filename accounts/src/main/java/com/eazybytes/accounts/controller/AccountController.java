package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constant.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ReponseDto;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.service.IAccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountController {

    IAccountService iAccountService;

    @GetMapping("hello")
    public String hello(){
        return "hi Amol";
    }

    @PostMapping("createAcc")
    public ResponseEntity<ReponseDto> createAccount(@RequestBody CustomerDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ReponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
    }

    @GetMapping("featchAcc")
    public ResponseEntity<CustomerDto> featchAccountDetails(@RequestParam String mobileNo){
        CustomerDto customerDto = iAccountService.featchAccountDetails(mobileNo);

        return new ResponseEntity<>(customerDto,HttpStatus.OK);
    }

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
    public ResponseEntity<ReponseDto> deleteAccountDetails(@RequestParam String mobileNo){
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
