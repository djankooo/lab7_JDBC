package tireShop;

public class Job {

    Integer jobID;

    String serviceName;
    Double servicePrice;

    String customerName;
    String customerSurname;
    Integer customerNumber;

    String jobDate;

    Boolean approved;

    public Job(int jobID, String customerName, String customerSurname, int customerNumber, String serviceName, Double servicePrice, String jobDate) {
        this.jobID = jobID;
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.customerNumber = customerNumber;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.jobDate = jobDate;
        this.approved = false;
    }


}
