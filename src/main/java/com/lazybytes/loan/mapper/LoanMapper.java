package com.lazybytes.loan.mapper;

import com.lazybytes.loan.dto.LoanDto;
import com.lazybytes.loan.entity.Loans;

public final class LoanMapper {

    private LoanMapper() {
        // private constructor to prevent instantiation
    }

    public static LoanDto mapToLoanDto(Loans loans, LoanDto loanDto) {

        loanDto.setMobileNumber(loans.getMobileNumber());
        loanDto.setLoanNumber(loans.getLoanNumber());
        loanDto.setLoanType(loans.getLoanType());
        loanDto.setTotalLoan(loans.getTotalLoan());
        loanDto.setAmountPaid(loans.getAmountPaid());
        loanDto.setOutstandingAmount(loans.getOutstandingAmount());
        return loanDto;
    }

    public static Loans mapToLoans(LoanDto loanDto, Loans loans) {

        loans.setMobileNumber(loanDto.getMobileNumber());
        loans.setLoanNumber(loanDto.getLoanNumber());
        loans.setLoanType(loanDto.getLoanType());
        loans.setTotalLoan(loanDto.getTotalLoan());
        loans.setAmountPaid(loanDto.getAmountPaid());
        loans.setOutstandingAmount(loanDto.getOutstandingAmount());
        return loans;
    }
}
