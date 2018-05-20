package MainPackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Transactions {
    
    public static void transferMoney(Account from, Account to, double amount) 
            throws FileNotFoundException, IOException{
        
        if(from.getBalance() < amount){
            JOptionPane.showMessageDialog(null, "You don't have enough balance");
        }else{
            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);
            
            JOptionPane.showMessageDialog(null, "Transfer was succefull");
        }
        
        File logFile = new File("/home/bug/Desktop/output.txt");
        FileWriter writer = new FileWriter(logFile.getAbsoluteFile(), true);
        try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            writer.write("An amount of " + amount + "$ was transfered from " + from.getCutomer().getName()
                    + "'s " + from.getAccountInfo() +" to " + to.getCutomer().getName() + "'s " + 
                    to.getAccountInfo() + " scuccefully");
            writer.write("\n");
            writer.write("\n");
        }
    }
    
    public static void checkAccount(Account account){
        
    }
    
    public static void giveLoan(Customer to, Loan loan){
        new Loan(loan,  to);
    }
    
    public static Loan checkLoanStatus(Loan loan){
        return loan;
    }
}
