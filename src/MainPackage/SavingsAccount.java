package MainPackage;

import java.util.Date;

public class SavingsAccount extends Account{
    
    private int intrestRate;

    public SavingsAccount(Customer customer, int intrestRate) {
        super(customer);
        this.intrestRate = intrestRate;
    }

    public int getInrestRate() {
        return intrestRate;
    }

    public void setInrestRate(int inrestRate) {
        this.intrestRate = inrestRate;
    }
    
    @Override
    public String getAccountInfo() {
        return "Savings Account";
    }
    
}
