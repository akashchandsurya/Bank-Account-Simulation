
import java.util.*;

public class Bank
{
    static Scanner s = new Scanner(System.in);
    static Map<String, Account> accounts = new HashMap<>();
    static Account currentAccount = null;

    public static void main(String[] args) 
    {
        int choice;

        do {
            System.out.println("\n--- Bank System Menu ---");
            System.out.println("1. Add Account");
            System.out.println("2. Show All Accounts");
            System.out.println("3. Select Account");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Check Balance");
            System.out.println("7. Show Transaction History");
            System.out.println("8. Delete Account");
            System.out.println("9. Exit"); 
            System.out.print("Enter your choice: ");
            choice = s.nextInt();
            s.nextLine();  // consume leftover newline

            switch (choice) {
                case 1:
                    addAccount();
                    break;
                case 2:
                    showAllAccounts();
                    break;
                case 3:
                    selectAccount();
                    break;
                case 4:
                    if (checkSelected()) 
                    {
                        System.out.print("Enter deposit amount: ");
                        double dep = s.nextDouble();
                        currentAccount.deposit(dep);
                    }
                    break;
                case 5:
                    if (checkSelected())
                    {
                        System.out.print("Enter withdraw amount: ");
                        double wit = s.nextDouble();
                        currentAccount.withdraw(wit);
                    }
                    break;
                case 6:
                    if (checkSelected()) 
                    {
                        currentAccount.checkBalance();
                    }
                    break;
                case 7:
                    if (checkSelected()) 
                    {
                        currentAccount.showTransactionHistory();
                    }
                    break;
                case 8:
                     deleteAccount();
                    break;
                case 9:
                     System.out.println("Thank you for using the Bank System!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 9);
    }

    public static boolean checkSelected() 
    {
        if (currentAccount == null) 
        {
            System.out.println("No account selected. Please select an account first.");
            return false;
        }
        return true;
    }

    // Add new account
    public static void addAccount() 
    {
        System.out.print("Enter new Account Holder Name: ");
        String name = s.nextLine();

        if (accounts.containsKey(name)) 
        {
            System.out.println("Account with this name already exists!");
            return;
        }

        System.out.print("Enter Initial Balance: ");
        double balance = s.nextDouble();

        Account account = new Account(name, balance);
        accounts.put(name, account);
        System.out.println("Account created successfully!");
    }

    // Delete existing account
    public static void deleteAccount() 
    {
        System.out.print("Enter Account Holder Name to delete: ");
        String name = s.nextLine();

        if (accounts.containsKey(name)) 
        {
            accounts.remove(name);
            if (currentAccount != null && currentAccount.getAccountHolder().equals(name)) 
            {
                currentAccount = null;
            }
            System.out.println("Account deleted successfully!");
        } else {
            System.out.println("Account not found.");
        }
    }

    // Select an account to operate
    public static void selectAccount() 
    {
        System.out.print("Enter Account Holder Name to select: ");
        String name = s.nextLine();

        if (accounts.containsKey(name))
        {
            currentAccount = accounts.get(name);
            System.out.println("Account selected: " + name);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Show all accounts in the system
    public static void showAllAccounts() {
        if (accounts.isEmpty()) 
        {
            System.out.println("No accounts available.");
            return;
        }

        System.out.println("\n--- All Accounts ---");
        for (Map.Entry<String, Account> entry : accounts.entrySet()) {
            System.out.println("Account Holder: " + entry.getKey() +
                               " | Balance: " + entry.getValue().getBalance());
        }
    }
}

// Account class
class Account 
{
    private String accountHolder;
    private double balance;
    private ArrayList<String> transactionHistory;

    public Account(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with balance: " + initialBalance);
    }

    public String getAccountHolder()
    {
        return accountHolder;
    }

    public double getBalance() 
    {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) 
        {
            balance += amount;
            transactionHistory.add("Deposited: " + amount);
            System.out.println("Successfully deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance)
        {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount);
            System.out.println("Successfully withdrew: " + amount);
        } 
        else
        {
            System.out.println("Invalid withdraw amount or insufficient balance!");
        }
    }

    public void checkBalance() 
    {
        System.out.println("Current Balance: " + balance);
    }

    public void showTransactionHistory() 
    {
        System.out.println("\nTransaction History for " + accountHolder + ":");
        for (String t : transactionHistory) 
        {
            System.out.println(t);
        }
    }
}