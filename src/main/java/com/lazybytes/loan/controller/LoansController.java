package com.lazybytes.loan.controller;

import com.lazybytes.loan.constant.LoanConstants;
import com.lazybytes.loan.dto.ErrorResponseDto;
import com.lazybytes.loan.dto.LoanDto;
import com.lazybytes.loan.dto.ResponseDto;
import com.lazybytes.loan.service.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Loans REST APIs for CRUD operations",
        description = "CRUD REST APIs for Lazy bank to CREATE, UPDATE, FETCH and DELETE loan information")
@RestController
@RequestMapping(path = "/api/loans", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class LoansController {

    private final ILoanService iLoanService;

    public LoansController(ILoanService iLoanService) {
        this.iLoanService = iLoanService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Operation(
            summary = "Create loan REST API",
            description = "REST API to create loan for a customer in Lazy Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status 201 CREATED"
            )}
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {

        iLoanService.createLoanAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));
    }
    @Operation(
            summary = "Fetch LOAN REST API",
            description = "REST API to fetch loan for a customer in Lazy Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status 200 FOUND"
            )}
    )
    @GetMapping("/fetch")
    public ResponseEntity<LoanDto> fetchLoanDetails(@RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")String mobileNumber) {

        LoanDto loanDto = iLoanService.getLoanDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(loanDto);
    }

    @Operation(
            summary = "UPDATE LOAN REST API",
            description = "REST API to update loan info for a existing loan holder in Lazy Bank."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status EXPECTATION FAILED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )

            )
    })
    @PutMapping("/update")
    public ResponseEntity<LoanDto> updateLoanDetails(@RequestBody @Valid LoanDto loanDto) {

        LoanDto updatedLoan = iLoanService.updateLoanDetails(loanDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedLoan);
    }

    @Operation(
            summary = "DELETE LOAN REST API",
            description = "REST API to delete loan holder info in Lazy Bank."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status EXPECTATION FAILED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )

            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoanAccount(@RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")String mobileNumber) {

        boolean isDeleted = iLoanService.deleteLoanAccount(mobileNumber);
        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Fetch LOAN REST API",
            description = "REST API to fetch loan for a customer in Lazy Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status 200 FOUND"
            )}
    )
    @GetMapping("/version-info")
    public ResponseEntity<ResponseDto> fetchVersionDetails() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new ResponseDto(LoanConstants.STATUS_200,
                        "Loan Microservice build version is: " + buildVersion));
    }

}
