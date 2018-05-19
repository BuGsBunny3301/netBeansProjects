package MainPackage;

import java.util.Date;

public class CreditAccount extends Account{

    public CreditAccount(Customer customer) {
        super(customer);
    }


    @Override
    public String getAccountInfo() {
        return "Credit Account";
    }
    
}
