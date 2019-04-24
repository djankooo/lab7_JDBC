package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tireShop.Customer;
import tireShop.Job;
import tireShop.Service;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    Statement statement;
    Connection conn = null;

    public Database() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        //connect();
        createNewTable();
    }

    private Connection connect() throws SQLException {
        return conn = DriverManager.getConnection("jdbc:sqlite:myDB.sqlite");
    }

    public void createNewTable() {

        // SQLite connection string
        String url = "jdbc:sqlite:myDB.sqlite";

        // SQL statement for creating a new table
        String customerList = "CREATE TABLE IF NOT EXISTS customerList (\n"
                + "	customerID integer PRIMARY KEY,\n"
                + "	customerName text NOT NULL,\n"
                + "	customerSurname text NOT NULL,\n"
                + "	customerNumber integer NOT NULL\n"
                + ");";

        String servicesList = "CREATE TABLE IF NOT EXISTS servicesList (\n"
                + "	serviceID integer PRIMARY KEY,\n"
                + "	serviceName text NOT NULL,\n"
                + "	servicePrice real NOT NULL\n"
                + ");";

        String mainList = "CREATE TABLE IF NOT EXISTS mainList (\n"
                + "	mainID integer PRIMARY KEY,\n"
                + "	mainCustomer integer NOT NULL,\n"
                + "	mainService integer NOT NULL,\n"
                + "	jobDate text NOT NULL\n,"
                + " FOREIGN KEY (mainCustomer) REFERENCES customerList(customerID)\n,"
                + " FOREIGN KEY (mainService) REFERENCES servicesList(serviceID)\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
            stmt.execute(customerList);
            stmt.execute(servicesList);
            stmt.execute(mainList);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error createNewTable");
        }
    }

    public void insertToCustomerList(String customerName, String customerSurname, int customerNumber) {
        String sql = "INSERT INTO customerList(customerName,customerSurname,customerNumber) VALUES(?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customerName);
            pstmt.setString(2, customerSurname);
            pstmt.setInt(3, customerNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error insertToCustomerList");
        }
    }
    public void insertToServicesList(String serviceName, Double servicePrice) {
        String sql = "INSERT INTO servicesList(serviceName,servicePrice) VALUES(?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, serviceName);
            pstmt.setDouble(2, servicePrice);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error insertToServicesList");
        }
    }
    public void insertToMainList(int mainCustomer, int mainService, String mainDate) {
        String sql = "INSERT INTO mainList(mainCustomer, mainService, jobDate) VALUES(?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, mainCustomer);
            pstmt.setInt(2, mainService);
            pstmt.setString(3, mainDate);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error insertToMainList");

        }
    }

    public void selectAllCustomerList(){
        String customerList = "SELECT customerID, customerName, customerSurname, customerNumber FROM customerList";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(customerList)){

            while (rs.next()) {
                System.out.println(
                        rs.getInt("customerID") +  "\t" +
                        rs.getString("customerName") + "\t" +
                        rs.getString("customerSurname") + "\t" +
                        rs.getDouble("customerNumber")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error selectAllCustomerList");
        }
    }
    public void selectAllServicesList(){
        String customerList = "SELECT serviceID, serviceName, servicePrice FROM servicesList";
        try (Connection conn = this.connect();

             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(customerList)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(
                        rs.getInt("serviceID") +  "\t" +
                        rs.getString("serviceName") +  "\t" +
                        rs.getDouble("servicePrice")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error selectAllServicesList");
        }
    }
    public void selectAllMainList(){
        String mainList = "SELECT mainID, mainCustomer, mainService, mainDate FROM mainList";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(mainList)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(
                        rs.getInt("mainID") +  "\t" +
                        rs.getInt("mainCustomer") + "\t" +
                        rs.getInt("mainService") +  "\t" +
                        rs.getString("mainDate")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error selectAllMainList");
        }
    }
    public ObservableList getAllCustomerList(){

        ArrayList <Customer> customerArrayList = new ArrayList<>();
        ObservableList data = null;
        String customerList = "SELECT customerID, customerName, customerSurname, customerNumber FROM customerList";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(customerList)){

            while (rs.next()) {
                customerArrayList.add(new Customer(rs.getInt("customerID"),rs.getString("customerName"),rs.getString("customerSurname"), rs.getInt("customerNumber")));
            }

            data = FXCollections.observableList(customerArrayList);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error getAllCustomerList");
        }
        return data;
    }
    public ObservableList getAllServicesList(){

        ArrayList <Service> servicesArrayList = new ArrayList<>();
        ObservableList data = null;
        String servicesList = "SELECT serviceID, serviceName, servicePrice FROM servicesList";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(servicesList)){

            while (rs.next()) {
                servicesArrayList.add(new Service(rs.getInt("serviceID"),rs.getString("serviceName"),rs.getDouble("servicePrice")));
            }

            data = FXCollections.observableList(servicesArrayList);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error getAllCustomerList");
        }
        return data;
    }
    public ObservableList getAllMainList(){

        ObservableList<Job> personData = FXCollections.observableArrayList();

        String mainList = "SELECT mainID, jobDate, c.customerName, c.customerSurname, c.customerNumber, s.serviceName, s.servicePrice FROM mainList as m INNER JOIN servicesList as s ON m.mainService=s.serviceID INNER JOIN customerList as c ON m.mainCustomer=c.customerID";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(mainList)){

            while (rs.next()) {
                personData.add(new Job(
                        rs.getInt("mainID"),
                        rs.getString("customerName"),
                        rs.getString("customerSurname"),
                        rs.getInt("customerNumber"),
                        rs.getString("serviceName"),
                        rs.getDouble("servicePrice"),
                        rs.getString("jobDate")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error getAllMainList");
        }
        return personData;
    }













}
