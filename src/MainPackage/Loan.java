package MainPackage;

public class Loan {
    private double amount;
    private float intrestRate;
    private float paymentPeriod;
    private double penalty;

//    public Loan(Customer customer, double amount, float intrestRate, float paymentPeriod) {
//        this.amount = amount;
//        this.intrestRate = intrestRate;
//        this.paymentPeriod = paymentPeriod;
//        customer.setLoan(Loan.this);
//    }

    public Loan(Loan loan, Customer customer){
        this.amount = loan.amount;
        this.intrestRate = loan.intrestRate;
        this.paymentPeriod = loan.paymentPeriod;
        customer.setLoan(loan);
    }

    public Loan(double amount, float intrestRate, float paymentPeriod) {
        this.amount = amount;
        this.intrestRate = intrestRate;
        this.paymentPeriod = paymentPeriod;
    }
    
    
    
    public double getAmount() {
        return amount;
    }

    public float getIntrestRate() {
        return intrestRate;
    }

    public float getPaymentPeriod() {
        return paymentPeriod;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setIntrestRate(float intrestRate) {
        this.intrestRate = intrestRate;
    }

    public void setPaymentPeriod(float paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }
}
