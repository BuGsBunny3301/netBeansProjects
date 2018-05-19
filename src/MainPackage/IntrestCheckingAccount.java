package MainPackage;

import java.util.Date;

public class IntrestCheckingAccount extends Account{

    public IntrestCheckingAccount(Customer customer) {
        super(customer);
    }

    
    @Override
    public String getAccountInfo() {
        return "This is an Intrest Checking Account";
    }
    
}
