package MainPackage;

import java.util.Date;

public class SavingsAccount extends Account{

    public SavingsAccount(Customer customer) {
        super(customer);
    }


    @Override
    public String getAccountInfo() {
        return "This is a Savings Account";
    }
    
}
