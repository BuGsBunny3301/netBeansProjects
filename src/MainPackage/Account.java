package MainPackage;

import java.util.Date;

public abstract class Account{
    private Customer customer;
    private Date created;
    private double balance;
    private int accountId;
    static int id;
//    private Loan loan;

    public Account(Customer customer, double balance) {
        this.customer = customer;
        created = new Date();
        customer.addAccount(Account.this);
        this.balance = balance;
        id++;
        accountId = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getAccountId() {
        return accountId;
    }
    
    public Date getCreated() {
        return created;
    }

    public double getBalance() {
        return balance;
    }

    public void setCutomer(Customer cutomer) {
        this.customer = cutomer;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public abstract String getAccountInfo();
}
