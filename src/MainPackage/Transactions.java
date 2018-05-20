package MainPackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Transactions {
    
    public static void transferMoney(Account from, Account to, double amount) 
            throws FileNotFoundException, IOException{
        
        File logFile = new File("/home/bug/Desktop/output.txt");
        FileWriter writer = new FileWriter(logFile.getAbsoluteFile(), true);
        
        if(from.getBalance() < amount){
            JOptionPane.showMessageDialog(null, "You don't have enough balance");
            try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                writer.write("Failed to transfer "+ amount +  "$ from " + from.getCustomer().getName() + "'s " + from.getAccountInfo()
                + " to " + to.getCustomer().getName() + "'s " + to.getAccountInfo());
                writer.write("\n");
                writer.write("\n");
            }
        }else{
            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);
            
            JOptionPane.showMessageDialog(null, "Transfer was succefull");
            try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                writer.write("An amount of " + amount + "$ was transfered from " + from.getCustomer().getName()
                        + "'s " + from.getAccountInfo() +" to " + to.getCustomer().getName() + "'s " + 
                        to.getAccountInfo() + " scuccefully");
                writer.write("\n");
                writer.write("\n");
            }
        }
    }
    
    public static void drawMoney(Account account, double amount) throws IOException{
        
        File logFile = new File("/home/bug/Desktop/output.txt");
        FileWriter writer = null;
        try {
            writer = new FileWriter(logFile.getAbsoluteFile(), true);
        } catch (IOException ex) {
            Logger.getLogger(Transactions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(account.getBalance() > amount){
            account.setBalance(account.getBalance() - amount);
            JOptionPane.showMessageDialog(null, "Money Draw Succefull");
            
            try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                    writer.write(amount + "$ was drawn from " + account.getCustomer().getName() + "'s " + 
                            account.getAccountInfo());
                    writer.write("\n");
                    writer.write("\n");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Money Draw Failed");
            
            try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                    writer.write("Failed to draw " + amount + "$ from " + account.getCustomer().getName() + "'s " +
                            account.getAccountInfo());
                    writer.write("\n");
                    writer.write("\n");
            }
        }
    }
    
    public static void addMoney(Account account, double amount) throws IOException{
        
        account.setBalance(account.getBalance() + amount);
        
        File logFile = new File("/home/bug/Desktop/output.txt");
        FileWriter writer = null;
        try {
            writer = new FileWriter(logFile.getAbsoluteFile(), true);
        } catch (IOException ex) {
            Logger.getLogger(Transactions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JOptionPane.showMessageDialog(null, "Money Draw Succefull");
            
            try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                    writer.write(amount + "$ was added to " + account.getCustomer().getName() + "'s " + 
                            account.getAccountInfo());
                    writer.write("\n");
                    writer.write("\n");
            }
    }
    
    public static void giveLoan(Customer to, Loan loan){
        new Loan(loan,  to);
    }
}
