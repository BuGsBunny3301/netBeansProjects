package MainPackage;

import java.util.Date;

public class CheckingAccount extends Account{

    public CheckingAccount(Customer customer, double balance) {
        super(customer, balance);
    }

    
    @Override
    public String getAccountInfo() {
        return "Checking Account";
    }
    
}
