package tireShop;

import Models.Database;
import Models.Language;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class CustomerSignUp extends Application {

    private Language language = new Language();
    private Database database = new Database();

    private ComboBox servicesComboBox;
    private ComboBox customersComboBox;

    private DatePicker dateDatePicker = new DatePicker();

    private Button submitButton = new Button();

    public CustomerSignUp(Language language) throws SQLException, ClassNotFoundException {
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

        pane.add(customersComboBox,0,0);
        pane.add(servicesComboBox,0,1);

        customersComboBox.setPrefSize(250,30);
        servicesComboBox.setPrefSize(250,30);
        dateDatePicker.setPrefSize(250,30);

        pane.add(dateDatePicker,0,2);
        pane.add(submitButton,0,3);

        pane.setHalignment(submitButton, HPos.RIGHT);
    }

    private void setTables(){

        servicesComboBox = new ComboBox(database.getAllServicesList());
        customersComboBox = new ComboBox(database.getAllCustomerList());

        Callback<ListView<Service>, ListCell<Service>> servicesComboBoxFactory = lv -> new ListCell<Service>() {

            @Override
            protected void updateItem(Service item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getServiceName() + " " + item.getServicePrice() + " zl");
            }

        };

        servicesComboBox.setCellFactory(servicesComboBoxFactory);
        servicesComboBox.setButtonCell(servicesComboBoxFactory.call(null));

        Callback<ListView<Customer>, ListCell<Customer>> customersComboBoxFactory = lv -> new ListCell<Customer>() {

            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getCustomerName() + " " + item.getCustomerSurname());
            }

        };

        customersComboBox.setCellFactory(customersComboBoxFactory);
        customersComboBox.setButtonCell(customersComboBoxFactory.call(null));
    }

    private void renameLabels(ResourceBundle bundle, Stage primaryStage){
        submitButton.setText(bundle.getString("my.summarize"));
        primaryStage.setTitle(bundle.getString("my.tireShop"));
    }



    @Override
    public void start(final Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();

        GridPane gridPane = createPane();

        borderPane.setCenter(gridPane);

        setTables();
        setUIControls(gridPane, primaryStage);

        primaryStage.setScene(new Scene(borderPane, 1000, 500));
        primaryStage.show();

        submitButton.setOnAction(event -> {

            Customer customer = (Customer) customersComboBox.getValue();
            Service service = (Service) servicesComboBox.getValue();

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            String date = dateFormatter.format(dateDatePicker.getValue()) + " 10:20:05.123";

            database.insertToMainList(customer.getId(), service.getServiceID(), date);
            //database.selectAllMainList();
        });
    }

    public static void main( String[] args ){
        launch(args);
    }

}
