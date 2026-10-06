import java.util.Scanner;

// Invalid PIN Exception
class InvalidPINException extends Exception {
    InvalidPINException(String message) {
        super(message);
    }
}

// Insufficient Balance Exception
class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String message) {
        super(message);
    }
}

// Invalid Amount Exception
class InvalidAmountException extends Exception {
    InvalidAmountException(String message) {
        super(message);
    }
}

// Withdrawal Limit Exception
class WithdrawalLimitException extends Exception {
    WithdrawalLimitException(String message) {
        super(message);
    }
}

public class ATMSystem {

    static double balance = 10000;
    static final int CORRECT_PIN = 1234;
    static final double WITHDRAWAL_LIMIT = 5000;

    // PIN verification
    static void verifyPIN(int pin) throws InvalidPINException {
        if (pin != CORRECT_PIN) {
            throw new InvalidPINException("Invalid PIN!");
        }
    }

    // Withdrawal
    static void withdraw(double amount)
            throws InvalidAmountException,
                   InsufficientBalanceException,
                   WithdrawalLimitException {

        if (amount <= 0) {
            throw new InvalidAmountException("Invalid transaction amount!");
        }

        if (amount > WITHDRAWAL_LIMIT) {
            throw new WithdrawalLimitException(
                    "Withdrawal limit exceeded! Maximum limit is ₹5000.");
        }

        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "Insufficient balance!");
        }

        balance = balance - amount;
        System.out.println("Withdrawal successful.");
        System.out.println("Remaining balance: ₹" + balance);
    }

    // Deposit
    static void deposit(double amount) throws InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException("Invalid deposit amount!");
        }

        balance = balance + amount;
        System.out.println("Deposit successful.");
        System.out.println("Current balance: ₹" + balance);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter PIN: ");
            int pin = sc.nextInt();

            verifyPIN(pin);

            System.out.println("\nLogin successful!");

            while (true) {

                System.out.println("\n--- ATM MENU ---");
                System.out.println("1. Balance Enquiry");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Exit");

                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                try {

                    switch (choice) {

                        case 1:
                            System.out.println(
                                    "Current balance: ₹" + balance);
                            break;

                        case 2:
                            System.out.print("Enter withdrawal amount: ");
                            double withdrawAmount = sc.nextDouble();

                            withdraw(withdrawAmount);
                            break;

                        case 3:
                            System.out.print("Enter deposit amount: ");
                            double depositAmount = sc.nextDouble();

                            deposit(depositAmount);
                            break;

                        case 4:
                            System.out.println("Thank you for using ATM!");
                            sc.close();
                            return;

                        default:
                            System.out.println("Invalid choice!");
                    }

                } catch (InvalidAmountException |
                         InsufficientBalanceException |
                         WithdrawalLimitException e) {

                    System.out.println("Error: " + e.getMessage());
                }
            }

        } catch (InvalidPINException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
