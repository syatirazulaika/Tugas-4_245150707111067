package Mart;

import java.util.*;

public class Main {

    private List<Cust> customers;

    public Main() {
        this.customers = new ArrayList<>();
    }

    public void addCustomer(Cust customer) {
        customers.add(customer);
    }

    public Cust findCustomer(String customerNumber) {
        for (Cust customer : customers) {
            if (customer.getCustomerNumber().equals(customerNumber)) {
                return customer;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main tinyMart = new Main();

        System.out.print("Enter number of customers to register: ");
        int numCustomers = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numCustomers; i++) {
            System.out.print("Enter Customer ID (10 digits): ");
            String customerID = scanner.nextLine();
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();
            System.out.print("Enter Initial Balance: ");
            double balance = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Enter Account Type (38-Silver, 56-Gold, 74-Platinum): ");
            scanner.nextLine();

            tinyMart.addCustomer(new Cust(name, customerID, balance, pin));
        }

        while (true) {
            System.out.println("\n1. Make a Purchase");
            System.out.println("2. Top-Up Balance");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 3) {
                System.out.println("Exiting...");
                break;
            }

            System.out.print("Enter Customer ID: ");
            String customerID = scanner.nextLine();
            Cust customer = tinyMart.findCustomer(customerID);

            if (customer == null) {
                System.out.println("Customer not found!");
                continue;
            }

            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();
            if (!customer.authenticate(pin)) {
                continue;
            }

            if (option == 1) {
                System.out.print("Enter purchase amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                customer.makeTransaction(amount);
            } else if (option == 2) {
                System.out.print("Enter top-up amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                customer.topUp(amount);
            }
        }
        scanner.close();
    }
}
