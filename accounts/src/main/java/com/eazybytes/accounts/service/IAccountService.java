package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;

public interface IAccountService {

    void createAccount(CustomerDto customerDto);

    CustomerDto featchAccountDetails(String mobileNo);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNo);
}
