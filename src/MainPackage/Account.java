package MainPackage;

import java.util.Date;

public abstract class Account{
    private Customer customer;
    private Date created;
//    private Loan loan;

    public Account(Customer customer) {
        this.customer = customer;
        created = new Date();
        customer.addAccount(Account.this);
    }

    public Customer getCutomer() {
        return customer;
    }

    public Date getCreated() {
        return created;
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

//    public Loan getLoans() {
//        return loan;
//    }

    public abstract String getAccountInfo();
}
