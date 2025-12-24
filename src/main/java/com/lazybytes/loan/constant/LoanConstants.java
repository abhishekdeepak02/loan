package com.lazybytes.loan.constant;

public final class LoanConstants {

    private LoanConstants() {
        // Private constructor to prevent instantiation
    }

    public static final String HOME_LOAN = "HOME_LOAN";
    public static final String PERSONAL_LOAN = "PERSONAL_LOAN";
    public static final String VEHICLE_LOAN = "VEHICLE_LOAN";

    public static final int NEW_LOAN_LIMIT = 1_00_000;
    public static final String  STATUS_201 = "201";
    public static final String  MESSAGE_201 = "Loan created successfully";
    public static final String  STATUS_200 = "200";
    public static final String  MESSAGE_200 = "Request processed successfully";
    public static final String  STATUS_417 = "417";
    public static final String  MESSAGE_417_UPDATE= "Update operation failed. Please try again or contact Dev team";
    public static final String  MESSAGE_417_DELETE= "Delete operation failed. Please try again or contact Dev team";
}
