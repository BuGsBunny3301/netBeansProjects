package MainPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Transactions {
    
    public static void transferMoney(Account from, Account to, double amount){
        File logFile = new File("C:\\Users\\user\\Desktop\\Transactions.txt");
        FileWriter writer = null;
        try {
            writer = new FileWriter(logFile.getAbsoluteFile(), true);
        } catch (IOException ex) {
            Logger.getLogger(Transactions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(from.getBalance() < amount){
            JOptionPane.showMessageDialog(null, "You don't have enough balance");
            try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                writer.write("Failed to transfer "+ amount +  "$ from " + from.getCustomer().getName() + "'s "
                        + from.getAccountInfo()
                + " to " + to.getCustomer().getName() + "'s " + to.getAccountInfo());
                writer.write("\r\n");
                writer.write("\r\n");
            } catch (IOException ex) {
                Logger.getLogger(Transactions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);
            
            JOptionPane.showMessageDialog(null, "Transfer was succefull");
            try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                writer.write("An amount of " + amount + "$ was transfered from " + from.getCustomer().getName()
                        + "'s " + from.getAccountInfo() +" to " + to.getCustomer().getName() + "'s " + 
                        to.getAccountInfo() + " scuccefully");
                writer.write("\r\n");
                writer.write("\r\n");
            } catch (IOException ex) {
                Logger.getLogger(Transactions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void readInfo() throws ParseException{
        final String FILENAME = "C:\\Users\\user\\Desktop\\Info.txt";
        
        BufferedReader br = null;
        FileReader fr = null;
        
        try {
                fr = new FileReader(FILENAME);
                br = new BufferedReader(fr);

                String sCurrentLine;
                String name, address;
                int phoneNumber, id;
                Date dob;
                double balance;

                while ((sCurrentLine = br.readLine()) != null) {
                                name = br.readLine();
                                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                                dob = format.parse(br.readLine());
                                address = br.readLine();
                                phoneNumber = Integer.parseInt(br.readLine());
                                id = Integer.parseInt(br.readLine());
                                Customers.customersList.add(new Customer(name, dob, address, phoneNumber, id));
                                br.readLine();
                                while (!(sCurrentLine = br.readLine()).equals("Done")){
                                    balance = Double.valueOf(br.readLine());
                                    switch(sCurrentLine){
                                        case "CreditAccount":
                                            new CreditAccount(Customers.customersList.get(Customers.customersList.size() - 1), balance);
                                            break;
                                        case "CheckingAccount":
                                            new CheckingAccount(Customers.customersList.get(Customers.customersList.size() - 1), balance);
                                            break;
                                        case "IntrestCheckingAccount":
                                            new IntrestCheckingAccount(Customers.customersList.get(Customers.customersList.size() - 1), balance, 2);
                                            break;
                                        case "SavingsAccount":
                                            new SavingsAccount(Customers.customersList.get(Customers.customersList.size() - 1), balance, 2);
                                            break;
                                    }
                                }
                }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    public static void drawMoney(Account account, double amount){
        
        File logFile = new File("C:\\Users\\user\\Desktop\\Transactions.txt");
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
                    writer.write("\r\n");
                    writer.write("\r\n");
            } catch (IOException ex) {
                Logger.getLogger(Transactions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Money Draw Failed");
            
            try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                    writer.write("Failed to draw " + amount + "$ from " + account.getCustomer().getName() + "'s " +
                            account.getAccountInfo());
                    writer.write("\r\n");
                    writer.write("\r\n");
            } catch (IOException ex) {
                Logger.getLogger(Transactions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void printInfo() throws FileNotFoundException, UnsupportedEncodingException, IOException{
    final String FILENAME = "C:\\Users\\user\\Desktop\\Info.txt";
        SimpleDateFormat sdf = new SimpleDateFormat(
            "MM/dd/yyyy");
        
        PrintWriter writer = null;
        
        try {
            writer = new PrintWriter(FILENAME, "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
        }
        
            try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                for (Customer cust : Customers.customersList) {
            writer.print(
                    "Customer:\r\n" +
                            cust.getName() + "\r\n" +
                            sdf.format(cust.getDob()) + "\r\n" +
                            cust.getAddress() + "\r\n" +
                            cust.getPhoneNumber()+ "\r\n" +
                            cust.getID() + "\r\n" +
                                    "Accounts:\r\n"
            );
            
            for(int j = 0; j < cust.getAccounts().size(); j++){
                switch(cust.getAccounts().get(j).getAccountInfo()){
                    case "Credit Account":
                        writer.print("CreditAccount\r\n" + cust.getAccounts().get(j).getBalance() + "\r\n");
//                        new CreditAccount(customer, balance);
                        break;
                    case "Checking Account":
                        writer.print("CheckingAccount\r\n" + cust.getAccounts().get(j).getBalance() + "\r\n");
//                        new CheckingAccount(customer, balance);
                        break;
                    case "Intrest Checking Account":
                        writer.print("IntrestCheckingAccount\r\n" + cust.getAccounts().get(j).getBalance() + "\r\n");
//                        new IntrestCheckingAccount(customer, balance, 2);
                        break;
                    case "Savings Account":
                        writer.print("SavingsAccount\r\n" + cust.getAccounts().get(j).getBalance() + "\r\n");
//                        new SavingsAccount(customer, balance, 2);
                        break;
                }
            }
            writer.print("Done\r\n");
        }
            }
    }
    
}
