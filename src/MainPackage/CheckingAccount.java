package MainPackage;

import java.util.Date;

public class CheckingAccount extends Account{

    public CheckingAccount(Customer customer) {
        super(customer);
    }

    
    @Override
    public String getAccountInfo() {
        return "Checking Account";
    }
    
}
