package com.lazybytes.loan.service.impl;

import com.lazybytes.loan.constant.LoanConstants;
import com.lazybytes.loan.dto.LoanDto;
import com.lazybytes.loan.entity.Loans;
import com.lazybytes.loan.exception.LoanAccountAlreadyExistException;
import com.lazybytes.loan.exception.ResourceNotFoundException;
import com.lazybytes.loan.mapper.LoanMapper;
import com.lazybytes.loan.repository.LoanRepository;
import com.lazybytes.loan.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanService implements ILoanService {

    private LoanRepository loanRepository;

    @Override
    public void createLoanAccount(String mobileNumber) {
        Optional<Loans> loanAccount = loanRepository.findByMobileNumber(mobileNumber);

        if(loanAccount.isPresent()) {
            throw new LoanAccountAlreadyExistException("Loan account already exist for mobile number: " + mobileNumber);

        }

        loanRepository.save(createAccount(mobileNumber));
    }

    @Override
    public LoanDto getLoanDetails(String mobileNumber) {

        Loans loanAccount = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(() ->
                new ResourceNotFoundException("Loan account not found for mobile number: " + mobileNumber));
        LoanDto loanDto = LoanMapper.mapToLoanDto(loanAccount, new LoanDto());
        return loanDto;

    }

    @Override
    public LoanDto updateLoanDetails(LoanDto loanDto) {

        Loans existingLoanAccount = loanRepository.findByMobileNumber(loanDto.getMobileNumber()).orElseThrow(() ->
                new ResourceNotFoundException("Loan account not found for mobile number: " + loanDto.getMobileNumber()));

        LoanMapper.mapToLoans(loanDto, existingLoanAccount);

        Loans updatedLoanAccount = loanRepository.save(existingLoanAccount);

        return LoanMapper.mapToLoanDto(updatedLoanAccount, new LoanDto());
    }

    @Override
    public boolean deleteLoanAccount(String mobileNumber) {
        boolean isDeleted = false;
        Loans loans = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(() ->
                new ResourceNotFoundException("Loan account not found for mobile number: " + mobileNumber));

        loanRepository.deleteById(loans.getLoanId());
        isDeleted = true;
        return isDeleted;
    }

    private Loans createAccount(String mobileNumber) {
        Loans loans = new Loans();
        loans.setMobileNumber(mobileNumber);
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        loans.setLoanNumber(Long.toString(randomLoanNumber));
        loans.setLoanType(LoanConstants.HOME_LOAN);
        loans.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        loans.setAmountPaid(0);
        loans.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        // manually adding - Let the JPA handle these fields
        //loans.setCreatedAt(LocalDateTime.now());
        //loans.setCreatedBy("SYSTEM");
        return loans;
    }
}
