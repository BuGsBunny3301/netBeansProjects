package MainPackage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {
    private String name;
    private Date dob;
    private String address;
    private int phoneNumber;
    private List<Account> accounts = new ArrayList<>();
    private int ID = 0;
    static int id = 0;

    public Customer() {
    }
    
    public Customer(String name, Date dob, String address, int phoneNumber) {
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        if(!Customers.customersList.isEmpty())
            id = Customers.customersList.get(Customers.customersList.size() - 1).getID();
        id++;
        ID = id;
    }
    
    public Customer(String name, Date dob, String address, int phoneNumber, int id) {
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.ID = id;
    }
    
    public void addAccount(Account account){
        this.accounts.add(account);
    }

    public String getName() {
        return name;
    }

    public Date getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setID(int id) {
        this.ID = id;
    }
}
