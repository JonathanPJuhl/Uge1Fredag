package dto;

import entities.BankCustomer;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Jonathan
 */
@Entity
public class CustomerDTO {
    @Id
    int customerID;
    String fullName;
    String accountNumber;
    double balance;

    public CustomerDTO(BankCustomer bC) {
        this.customerID = bC.getId();
        this.fullName = bC.getFirstName()+" "+bC.getLastName();
        this.accountNumber = bC.getAccountNumber();
        this.balance = bC.getBalance();
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public CustomerDTO() {
    }
    
}
