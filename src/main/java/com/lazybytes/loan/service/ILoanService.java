package com.lazybytes.loan.service;


import com.lazybytes.loan.dto.LoanDto;

public interface ILoanService {

    void createLoanAccount(String mobileNumber);

    LoanDto getLoanDetails(String mobileNumber);

    LoanDto updateLoanDetails(LoanDto loanDto);

    boolean deleteLoanAccount(String mobileNumber);
}
