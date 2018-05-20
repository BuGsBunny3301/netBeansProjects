package MainPackage;

import java.util.Date;

public abstract class Account{
    private Customer customer;
    private Date created;
    private double balance;
//    private Loan loan;

    public Account(Customer customer, double balance) {
        this.customer = customer;
        created = new Date();
        customer.addAccount(Account.this);
        this.balance = balance;
    }

    public Customer getCutomer() {
        return customer;
    }

    public Date getCreated() {
        return created;
    }

    public double getBalance() {
        return balance;
    }

//    public void setLoans(Loan Loans) {
//        this.loan = Loans;
//    }

    public void setCutomer(Customer cutomer) {
        this.customer = cutomer;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

//    public Loan getLoans() {
//        return loan;
//    }

    public abstract String getAccountInfo();
}
