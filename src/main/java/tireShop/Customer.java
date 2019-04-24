package tireShop;

public class Customer {

    private int customerID;
    private String customerName;
    private String customerSurname;
    private int customerNumber;

    public Customer(int id, String name, String surname, int number) {
        this.customerID = id;
        this.customerName = name;
        this.customerSurname = surname;
        this.customerNumber = number;
    }

    public int getId() {
        return customerID;
    }

    public void setId(int id) {
        this.customerID = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }
}
