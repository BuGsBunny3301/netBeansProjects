package MainPackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Transactions {
    
    public static void transferMoney(Account from, Account to, double amount) 
            throws FileNotFoundException, IOException{
        
        
        
        File logFile = new File("C:\\Users\\Adam Mawla\\Desktop\\output.txt");
        FileWriter fileWriter = new FileWriter(logFile.getAbsoluteFile(), true);
        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(" ");
            bufferedWriter.write("Stuff");
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
