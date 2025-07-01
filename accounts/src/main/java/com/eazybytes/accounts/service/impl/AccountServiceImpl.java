package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.constant.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExitsException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    /* -- We didn't use @Autowired annotation because we have used all args constructor -- */
    AccountsRepository accountsRepository;
    CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto){
        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());
        /** -- Finding present customer -- */
        Optional<Customer> presentCustomer = customerRepository.findByMobileNo(customer.getMobileNo());
        if(presentCustomer.isPresent())
            throw new CustomerAlreadyExitsException("Customer already registered with given mobile number "
                    +customer.getMobileNo());

        customer.setCreatedBy("Simple logic");
        customer.setCreatedAt(LocalDateTime.now());
        Customer customerSavedObj =  customerRepository.save(customer);
        Accounts accounts = createNewAccount(customerSavedObj);
        accountsRepository.save(accounts);
    }

    private Accounts createNewAccount(Customer customer){
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        /** generating random account number */
        long randomAccountNumber = 100000000L+new Random().nextInt(900000000);
        accounts.setAccountNumber(randomAccountNumber);
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
        accounts.setCreatedBy("Simple logic");
        accounts.setCreatedAt(LocalDateTime.now());
        return accounts;
    }

    public CustomerDto featchAccountDetails(String mobileNo){
        Customer customer = customerRepository.findByMobileNo(mobileNo)
                 .orElseThrow(
                         ()-> new ResourceNotFoundException("Customer","Mobile number",mobileNo)
                 );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow( ()-> new ResourceNotFoundException("Accounts","Customer id",customer.getCustomerId().toString()));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(new CustomerDto(),customer);
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    public boolean deleteAccount(String mobileNo){
        Customer customer = customerRepository.findByMobileNo(mobileNo)
                .orElseThrow( () -> new ResourceNotFoundException("Customer", "Customer Mobile no", mobileNo));

        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

}
