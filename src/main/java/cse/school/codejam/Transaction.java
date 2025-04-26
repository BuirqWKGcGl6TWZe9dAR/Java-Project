package cse.school.codejam;

import java.time.LocalDateTime;

public class Transaction {
    public enum TransactionType { DEPOSIT, WITHDRAW, TRANSFER }

    private TransactionType type;
    private String fromAccountNumber;
    private String toAccountNumber;
    private double amount;
    private final LocalDateTime timestamp = LocalDateTime.now();

    // Set the type of the transaction (deposit, withdraw, transfer)
    public Transaction setType(TransactionType type) {
        this.type = type; 
        return this;
    }

    // Set the from account number (relevant for transfers and withdrawals)
    public Transaction setFromAccountNumber(String from) {
        this.fromAccountNumber = from; 
        return this;
    }

    // Set the to account number (relevant for transfers and deposits)
    public Transaction setToAccountNumber(String to) {
        this.toAccountNumber = to; 
        return this;
    }

    // Set the amount for the transaction (ensures it's positive)
    public Transaction setAmount(double amt) {
        if (amt <= 0) {
            throw new IllegalArgumentException("Amount must be positive and greater than zero.");
        }
        this.amount = amt; 
        return this;
    }

    // Get the details of the transaction in a readable format
    public String getTransactionDetails() {
        String msg = "";
        
        // Build the message based on the type of transaction
        if (type == TransactionType.DEPOSIT) {
            // Deposit: Amount goes to the "to" account (the destination account)
            msg = "Deposit of Rs " + amount + " to account " + toAccountNumber;
        } else if (type == TransactionType.WITHDRAW) {
            // Withdraw: Amount comes from the "from" account (the source account)
            msg = "Withdrawal of Rs " + amount + " from account " + fromAccountNumber;
        } else if (type == TransactionType.TRANSFER) {
            // Transfer: Amount moves from "from" account to "to" account
            msg = "Transfer of Rs " + amount + " from account " + fromAccountNumber + " to account " + toAccountNumber;
        }

        // Return the complete message including the timestamp
        return msg + " on " + timestamp;
    }

    // Main method for testing the Transaction class
    public static void main(String[] args) {
        try {
            // Create a new deposit transaction
            Transaction t1 = new Transaction();
            t1.setType(TransactionType.DEPOSIT)
               .setFromAccountNumber("123456")  // From account is relevant for source
               .setToAccountNumber("789012")    // To account is relevant for destination
               .setAmount(500.00);  // Valid amount

            // Print transaction details
            System.out.println(t1.getTransactionDetails());  // Deposit message

            // Create a new withdrawal transaction
            Transaction t2 = new Transaction();
            t2.setType(TransactionType.WITHDRAW)
               .setFromAccountNumber("789012")  // From account is relevant for source
               .setToAccountNumber("123456")    // To account (not used in withdrawal)
               .setAmount(200.00);  // Valid amount

            // Print transaction details
            System.out.println(t2.getTransactionDetails());  // Withdrawal message

            // Create a new transfer transaction
            Transaction t3 = new Transaction();
            t3.setType(TransactionType.TRANSFER)
               .setFromAccountNumber("123456")  // From account for source
               .setToAccountNumber("789012")    // To account for destination
               .setAmount(300.00);  // Valid amount

            // Print transaction details
            System.out.println(t3.getTransactionDetails());  // Transfer message

        } catch (IllegalArgumentException e) {
            // Catch the exception for invalid amount
            System.out.println(e.getMessage());
        }
    }
}
