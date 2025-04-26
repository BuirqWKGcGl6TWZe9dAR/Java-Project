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
            msg = "Deposit of Rs " + amount + " to account " + fromAccountNumber;
        } else if (type == TransactionType.WITHDRAW) {
            msg = "Withdrawal of Rs " + amount + " from account " + toAccountNumber;
        } else if (type == TransactionType.TRANSFER) {
            msg = "Transfer of Rs " + amount + " from account " + fromAccountNumber + " to account " + toAccountNumber;
        }

        // Return the complete message including the timestamp
        return msg + " on " + timestamp;
    }

    // Main method for testing the Transaction class
    public static void main(String[] args) {
        try {
            // Create a new transaction
            Transaction t1 = new Transaction();
            t1.setType(TransactionType.DEPOSIT)
               .setFromAccountNumber("123456")
               .setAmount(500.00);  // Valid amount

            // Print transaction details
            System.out.println(t1.getTransactionDetails());

            // Attempting to set an invalid (zero) amount
            Transaction t2 = new Transaction();
            t2.setType(TransactionType.WITHDRAW)
               .setToAccountNumber("654321")
               .setAmount(0);  // This will throw an exception

        } catch (IllegalArgumentException e) {
            // Catch the exception for invalid amount
            System.out.println(e.getMessage());
        }
    }
}
