package MainPackage;

import java.util.Date;

public class IntrestCheckingAccount extends Account{

    private int intrestRate;
    
    public IntrestCheckingAccount(Customer customer, int intrestRate) {
        super(customer);
        this.intrestRate = intrestRate;
    }

    public int getIntrestRate() {
        return intrestRate;
    }

    public void setIntrestRate(int intrestRate) {
        this.intrestRate = intrestRate;
    }
    
    @Override
    public String getAccountInfo() {
        return "Intrest Checking Account";
    }
    
}
