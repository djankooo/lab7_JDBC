package tireShop;

import Models.Database;
import Models.Language;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ServiceSummaryMonth extends Application {

    private Language language;
    private Database database = new Database();

    private TableColumn jobIDCol;
    private TableColumn serviceCol;
    private TableColumn serviceNameCol;
    private TableColumn servicePriceCol;
    private TableColumn customerCol;
    private TableColumn customerNameCol;
    private TableColumn customerSurnameCol;
    private TableColumn customerNumberCol;
    private TableColumn dateCol;
    private TableColumn approvedCol;

    private Button submitButton = new Button("Add!");

    private final TableView table = new TableView<>();

    public ServiceSummaryMonth(Language language) throws SQLException, ClassNotFoundException {
        this.language = language;
    }
    private GridPane createPane() {

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setHgap(10);
        pane.setVgap(10);
        return pane;
    }
    private void setUIControls(GridPane pane, Stage primaryStage) throws Exception {

        renameLabels(language.getSelectedBundle(), primaryStage);

        table.setEditable(true);

        setTables();


        pane.add(table,0,0);
        pane.add(submitButton,0,1);

        pane.setHalignment(submitButton, HPos.RIGHT);
    }
    private void renameLabels(ResourceBundle bundle, Stage primaryStage){
        submitButton.setText(bundle.getString("my.summarize"));
        primaryStage.setTitle(bundle.getString("my.tireShop"));
    }
    public void setTables(){

        jobIDCol = new TableColumn("ID");
        serviceCol = new TableColumn("Service");
        customerCol = new TableColumn("Customer");
        dateCol = new TableColumn("Date");
        approvedCol = new TableColumn("Approved");

        serviceNameCol = new TableColumn("Name");
        servicePriceCol = new TableColumn("Price");

        customerSurnameCol = new TableColumn<>("Surname");
        customerNameCol = new TableColumn<>("Name");
        customerNumberCol = new TableColumn<>("Number");

        jobIDCol.setCellValueFactory(new PropertyValueFactory<Job,Integer>("jobID"));
        serviceNameCol.setCellValueFactory(new PropertyValueFactory<Job, String>("serviceName"));
        servicePriceCol.setCellValueFactory(new PropertyValueFactory<Job,Double>("servicePrice"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<Job, String>("customerName"));
        customerSurnameCol.setCellValueFactory(new PropertyValueFactory<Job, String>("customerSurname"));
        customerNumberCol.setCellValueFactory(new PropertyValueFactory<Job,Integer>("customerNumber"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Job, String>("jobDate"));
        approvedCol.setCellValueFactory(new PropertyValueFactory<Job, Boolean>("approved"));

        ObservableList <Job> observableList = database.getAllMainList();

        table.getColumns().addAll(jobIDCol, serviceCol, customerCol, dateCol, approvedCol);
        serviceCol.getColumns().addAll(serviceNameCol, servicePriceCol);
        customerCol.getColumns().addAll(customerNameCol, customerSurnameCol, customerNumberCol);

        table.setItems(observableList);
    }


    @Override
    public void start(final Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();

        GridPane gridPane = createPane();

        borderPane.setCenter(gridPane);

        setUIControls(gridPane, primaryStage);
        primaryStage.setScene(new Scene(borderPane, 1000, 500));
        primaryStage.show();

        submitButton.setOnAction(event -> {
            //database.insertToServicesList(serviceNameTextField.getText(),Double.valueOf(servicePriceTextField.getText()));
            //database.selectAllServicesList();
        });
    }

    public static void main( String[] args ) throws SQLException, ClassNotFoundException { launch(args); }
}
