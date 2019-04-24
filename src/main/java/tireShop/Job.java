package tireShop;

public class Job {


    private Integer jobID;

    private String serviceName;
    private Double servicePrice;

    private String customerName;
    private String customerSurname;
    private Integer customerNumber;

    private String jobDate;

    private Boolean approved;

    public Integer getJobID() {
        return jobID;
    }

    public void setJobID(Integer jobID) {
        this.jobID = jobID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Double servicePrice) {
        this.servicePrice = servicePrice;
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

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getJobDate() {
        return jobDate;
    }

    public void setJobDate(String jobDate) {
        this.jobDate = jobDate;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }



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
