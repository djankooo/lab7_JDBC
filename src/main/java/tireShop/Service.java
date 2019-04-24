package tireShop;

public class Service {

    private int serviceID;
    private String serviceName;
    private Double servicePrice;

    public Service(int ID, String name, Double price) {
        this.serviceID = ID;
        this.serviceName = name;
        this.servicePrice = price;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
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
}
