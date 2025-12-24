package com.lazybytes.loan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
        name = "Loan",
        description = "Schema to hold Loan information"
)
@Data
public class LoanDto {

    @Schema(
            description = "Mobile Number of the Customer",
            example = "9876543210"
    )
    @NotEmpty(message = "Mobile number cannot be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
    private String mobileNumber;
    @Schema(
            description = "Loan Number of the Customer",
            example = "123456789012"
    )
    @NotEmpty(message = "Loan number cannot be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Loan number must be 12 digits.")
    private String loanNumber;

    @Schema(
            description = "Type of the Loan",
            example = "Home Loan"
    )
    @NotEmpty(message = "Loan type cannot be empty.")
    private String loanType;
    @Schema(
            description = "Total Loan Amount",
            example = "500000.00"
    )
    @Positive(message = "Total loan amount should be greater than 0.")
    private double totalLoan;
    @Schema(
            description = "Amount Paid towards the Loan",
            example = "150000.00"
    )
    @PositiveOrZero(message = "Amount paid should be 0 or greater than 0.")
    private double amountPaid;
    @Schema(
            description = "Outstanding Amount of the Loan",
            example = "350000.00"
    )
    @PositiveOrZero(message = "Outstanding amount should be 0 or greater than 0.")
    private double outstandingAmount;
}
