package MainPackage;

import java.util.Date;

public class CheckingAccount extends Account{

    public CheckingAccount(Customer customer) {
        super(customer);
    }

    
    @Override
    public String getAccountInfo() {
        return "This is a Checking Account";
    }
    
}
