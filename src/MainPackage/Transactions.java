package MainPackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Transactions {
    
    public static void transferMoney(Account from, Account to, double amount) throws FileNotFoundException, IOException{
        File logFile = new File("/home/bug/Desktop/output.txt");
        FileWriter fileWriter = new FileWriter(logFile.getAbsoluteFile(), true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(" "); 
        bufferedWriter.write("Stuff");
        bufferedWriter.close();
//        PrintWriter log_file_writer = new PrintWriter(logFile);
//        log_file_writer.println("TEXT");
//        log_file_writer.flush();
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
