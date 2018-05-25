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
        
        File logFile = new File("/home/bug/Desktop/Transactions.txt");
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
                writer.write("\n");
                writer.write("\n");
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
                writer.write("\n");
                writer.write("\n");
            } catch (IOException ex) {
                Logger.getLogger(Transactions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void readTransactions(){
        
        final String FILENAME = "/home/bug/Desktop/Transactions.txt";
        
        BufferedReader br = null;
        FileReader fr = null;
        
        try {
                fr = new FileReader(FILENAME);
                br = new BufferedReader(fr);

                String sCurrentLine;

                while ((sCurrentLine = br.readLine()) != null) {
                        JOptionPane.showMessageDialog(null, sCurrentLine);
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
    
    public static void addCustomer(Customer customer){
        final String FILENAME = "/home/bug/Desktop/Files/" + customer.getName() + customer.getID() + ".txt";
        SimpleDateFormat sdf = new SimpleDateFormat(
            "MM/dd/yyyy");
        
        PrintWriter writer = null;
        
        try {
            writer = new PrintWriter(FILENAME, "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(Transactions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                writer.write(
                        "Customer:\n" +
                                customer.getName() + "\n" +
                                sdf.format(customer.getDob()) + "\n" +
                                customer.getAddress() + "\n" +
                                customer.getPhoneNumber() + "\n" +
                                customer.getLoan() + "\n" +
                                customer.getID() + "\n" +
                                "Accounts:\n"
                );
            } catch (IOException ex) {
                Logger.getLogger(Transactions.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static void copyText() throws FileNotFoundException, IOException{
        FileChannel src = new FileInputStream("/home/bug/Desktop/Info.txt").getChannel();
        FileChannel dest = new FileOutputStream("/home/bug/Desktop/input.txt").getChannel();
        dest.transferFrom(src, 0, src.size());
    }
    
    public static void eraseText() throws FileNotFoundException, IOException{
        PrintWriter pw = new PrintWriter("/home/bug/Desktop/Info.txt");
        BufferedReader br1 = new BufferedReader(new FileReader("/home/bug/Desktop/input.txt"));
        String line1 = br1.readLine();
        
         while(line1 != null)
        {
            boolean flag = false;
             
            // BufferedReader object for delete.txt
            BufferedReader br2 = new BufferedReader(new FileReader("/home/bug/Desktop/delete.txt"));
             
            String line2 = br2.readLine();
             
            // loop for each line of delete.txt
            while(line2 != null)
            {
                if(line1.equals(line2))
                {
                    flag = true;
                    break;
                }
                 
                line2 = br2.readLine();
            }
             
            // if flag = false
            // write line of input.txt to output.txt
            if(!flag)
                pw.println(line1);
             
            line1 = br1.readLine();
             
        }
         
        pw.flush();
         
        // closing resources
        br1.close();
        pw.close();
         
        System.out.println("File operation performed successfully");
        
    }
    
    public static void addAccount(Customer customer, String type, double balance) throws IOException{
        
         final String FILENAME = "/home/bug/Desktop/Info.txt";
        
//        File logFile = new File("/home/bug/Desktop/delete.txt");
//        FileWriter writer = null;
//        try {
//            writer = new FileWriter(logFile.getAbsoluteFile(), true);
//        } catch (IOException ex) {
//            Logger.getLogger(Transactions.class.getName()).log(Level.SEVERE, null, ex);
//        }

            SimpleDateFormat sdf = new SimpleDateFormat(
                "MM/dd/yyyy");
        
        copyText();
        
        PrintWriter writer1 = new PrintWriter("/home/bug/Desktop/delete.txt");
        writer1.print(
                "Customer:\n" +
                        customer.getName() + "\n" +
                        sdf.format(customer.getDob()) + "\n" +
                        customer.getAddress() + "\n" +
                        customer.getPhoneNumber()+ "\n" +
                        customer.getLoan() + "\n" +
                        customer.getID() + "\n" +
                        "Accounts:\n"
        );
        for(int i = 0; i < customer.getAccounts().size(); i++){
            switch(customer.getAccounts().get(i).getAccountInfo()){
                case "Credit Account":
                        writer1.print("CreditAccount\n" + customer.getAccounts().get(i).getBalance() + "\n");
//                        new CreditAccount(customer, balance);
                        break;
                    case "Checking Account":
                        writer1.print("CheckingAccount\n" + customer.getAccounts().get(i).getBalance() + "\n");
//                        new CheckingAccount(customer, balance);
                        break;
                    case "Intrest Checking Account":
                        writer1.print("IntrestCheckingAccount\n" + customer.getAccounts().get(i).getBalance() + "\n");
//                        new IntrestCheckingAccount(customer, balance, 2);
                        break;
                    case "Savings Account":
                        writer1.print("SavingsAccount\n" + customer.getAccounts().get(i).getBalance() + "\n");
//                        new SavingsAccount(customer, balance, 2);
                        break;
            }
        }
        
        writer1.print("Done");
        
        writer1.close();
        
        eraseText();
        
        
        File logFile = new File(FILENAME);
        FileWriter writer = null;
        try {
            writer = new FileWriter(logFile.getAbsoluteFile(), true);
        } catch (IOException ex) {
            Logger.getLogger(Transactions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                writer.write(
                        "Customer:\n" +
                                customer.getName() + "\n" +
                                sdf.format(customer.getDob()) + "\n" +
                                customer.getAddress() + "\n" +
                                customer.getPhoneNumber() + "\n" +
                                customer.getLoan() + "\n" +
                                customer.getID() + "\n" +
                                "Accounts:\n"
                );
                
                for(int i = 0; i < customer.getAccounts().size() - 1; i++){
                    switch(customer.getAccounts().get(i).getAccountInfo()){
                        case "Credit Account":
                                writer.write("CreditAccount\n" + customer.getAccounts().get(i).getBalance() + "\n");
        //                        new CreditAccount(customer, balance);
                                break;
                            case "Checking Account":
                                writer.write("CheckingAccount\n" + customer.getAccounts().get(i).getBalance() + "\n");
        //                        new CheckingAccount(customer, balance);
                                break;
                            case "Intrest Checking Account":
                                writer.write("IntrestCheckingAccount\n" + customer.getAccounts().get(i).getBalance() + "\n");
        //                        new IntrestCheckingAccount(customer, balance, 2);
                                break;
                            case "Savings Account":
                                writer.write("SavingsAccount\n" + customer.getAccounts().get(i).getBalance() + "\n");
        //                        new SavingsAccount(customer, balance, 2);
                                break;
                    }
        }
            switch(type){
                    case "CreditAccount":
                        writer.write("CreditAccount\n" + balance + "\nDone");
//                        new CreditAccount(customer, balance);
                        break;
                    case "CheckingAccount":
                        writer.write("CheckingAccount\n" + balance + "\nDone");
//                        new CheckingAccount(customer, balance);
                        break;
                    case "IntrestCheckingAccount":
                        writer.write("IntrestCheckingAccount\n" + balance + "\nDone");
//                        new IntrestCheckingAccount(customer, balance, 2);
                        break;
                    case "SavingsAccount":
                        writer.write("SavingsAccount\n" + balance + "\nDone");
//                        new SavingsAccount(customer, balance, 2);
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(Transactions.class.getName()).log(Level.SEVERE, null, ex);
            }
        
//        File logFile = new File(FILENAME);
//        FileWriter writer = null;
//        try {
//            writer = new FileWriter(logFile.getAbsoluteFile(), true);
//        } catch (IOException ex) {
//            Logger.getLogger(Transactions.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//            try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
//                
//                switch(type){
//                    case "CreditAccount":
//                        writer.write("CreditAccount\n" + balance + "\nDone");
////                        new CreditAccount(customer, balance);
//                        break;
//                    case "CheckingAccount":
//                        writer.write("CheckingAccount\n" + balance + "\nDone");
////                        new CheckingAccount(customer, balance);
//                        break;
//                    case "IntrestCheckingAccount":
//                        writer.write("IntrestCheckingAccount\n" + balance + "\nDone");
////                        new IntrestCheckingAccount(customer, balance, 2);
//                        break;
//                    case "SavingsAccount":
//                        writer.write("SavingAccount\n" + balance + "\nDone");
////                        new SavingsAccount(customer, balance, 2);
//                        break;
//                }
//                
////                writer.write(
////                        
////                );
//            } catch (IOException ex) {
//                Logger.getLogger(Transactions.class.getName()).log(Level.SEVERE, null, ex);
//            }
        
//        BufferedReader br = null;
//        FileReader fr = null;
//        
//        try {
//                fr = new FileReader("/home/bug/Desktop/Info.txt");
//                br = new BufferedReader(fr);
//
//                String sCurrentLine;
////                while((sCurrentLine = br.readLine()) != null) {
////                    if(sCurrentLine.equals("Accounts:")){
//                    try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
//                        writer.write("Testing");
//                    } catch (IOException ex) {
//                        Logger.getLogger(Transactions.class.getName()).log(Level.SEVERE, null, ex);
//                    }
////                    }
////                }
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//        finally{
//            try{
//                if (br != null)
//                    br.close();
//
//                if (fr != null)
//                    fr.close();
//            }catch(IOException e){
//                e.printStackTrace();
//            }
//        }
    }
    
    public static void readInfo() throws ParseException{
        final String FILENAME = "/home/bug/Desktop/Info.txt";
        
        BufferedReader br = null;
        FileReader fr = null;
        
        try {
                fr = new FileReader(FILENAME);
                br = new BufferedReader(fr);

                String sCurrentLine;
                String name, address;
                int phoneNumber, id;
                Date dob;
                Loan loan;
                double balance;
                ArrayList<Account> accounts = new ArrayList<>();

                while ((sCurrentLine = br.readLine()) != null) {
                                name = br.readLine();
                                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                                dob = format.parse(br.readLine());
                                address = br.readLine();
                                phoneNumber = Integer.parseInt(br.readLine());
                                sCurrentLine = br.readLine();
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
        
        File logFile = new File("/home/bug/Desktop/Transactions.txt");
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
            } catch (IOException ex) {
                Logger.getLogger(Transactions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Money Draw Failed");
            
            try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                    writer.write("Failed to draw " + amount + "$ from " + account.getCustomer().getName() + "'s " +
                            account.getAccountInfo());
                    writer.write("\n");
                    writer.write("\n");
            } catch (IOException ex) {
                Logger.getLogger(Transactions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void addMoney(Account account, double amount){
        
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
            } catch (IOException ex) {
            Logger.getLogger(Transactions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void giveLoan(Customer to, Loan loan){
        new Loan(loan,  to);
    }
}
