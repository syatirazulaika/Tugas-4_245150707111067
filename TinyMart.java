package Mart;

public class Cust {
    private String name;
    private String customerNumber;
    private double balance;
    private String pin;
    private boolean isBlocked;
    private int failedAttempts;

    public Cust(String name, String customerNumber, double balance, String pin) {
        this.name = name;
        this.customerNumber = customerNumber;
        this.balance = balance;
        this.pin = pin;
        this.isBlocked = false;
        this.failedAttempts = 0;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public boolean authenticate(String inputPin) {
        if (isBlocked) {
            System.out.println("Akun diblokir!");
            return false;
        }
        if (this.pin.equals(inputPin)) {
            failedAttempts = 0;
            return true;
        } else {
            failedAttempts++;
            if (failedAttempts >= 3) {
                isBlocked = true;
                System.out.println("Akun diblokir karena 3 kali kesalahan input PIN!");
            }
            return false;
        }
    }

    public void makeTransaction(double amount) {
        if (balance - amount < 10000) {
            System.out.println("Saldo tidak mencukupi!");
            return;
        }
        balance -= amount;
        applyCashback(amount);
        System.out.println("Pembelian berhasil! Saldo baru: " + String.format("%.2f", balance));
    }

    public void topUp(double amount) {
        balance += amount;
        System.out.println("Top-up successful! New balance: " + String.format("%.2f", balance));
    }

    private void applyCashback(double amount) {
        String type = customerNumber.substring(0, 2);
        double cashback = 0;
        if (type.equals("38") && amount > 1000000) cashback = amount * 0.05;
        else if (type.equals("56")) cashback = amount > 1000000 ? amount * 0.07 : amount * 0.02;
        else if (type.equals("74")) cashback = amount > 1000000 ? amount * 0.10 : amount * 0.05;
        balance += cashback;
        System.out.println("Cashback diberikan: " + String.format("%.2f", cashback));
    }
}
