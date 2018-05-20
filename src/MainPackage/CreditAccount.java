package MainPackage;

import java.util.Date;

public class CreditAccount extends Account{

    public CreditAccount(Customer customer, double balance) {
        super(customer, balance);
    }


    @Override
    public String getAccountInfo() {
        return "Credit Account";
    }
    
}
