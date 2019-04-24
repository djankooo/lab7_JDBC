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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerAdd extends Application {

    Language language = new Language();
    Database database = new Database();

    Label customerNameLabel = new Label();
    Label customerSurnameLabel = new Label();
    Label customerNumberLabel = new Label();
    TextField customerNameTextField = new TextField();
    TextField customerSurnameTextField = new TextField();
    TextField customerNumberTextField = new TextField();

    Button submitButton = new Button("Add!");

    public CustomerAdd(Language language) throws SQLException, ClassNotFoundException {
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

        pane.add(customerNameLabel,0,0);
        pane.add(customerSurnameLabel,0,1);
        pane.add(customerNameTextField,1,0);
        pane.add(customerSurnameTextField,1,1);
        pane.add(customerNumberLabel,0,2);
        pane.add(customerNumberTextField,1,2);
        pane.add(submitButton,1,3);

        pane.setHalignment(submitButton, HPos.RIGHT);


    }

    private void renameLabels(ResourceBundle bundle, Stage primaryStage){
        customerNameLabel.setText(bundle.getString("my.customerName"));
        customerSurnameLabel.setText(bundle.getString("my.customerSurname"));
        customerNumberLabel.setText(bundle.getString("my.customerNumber"));
        submitButton.setText(bundle.getString("my.summarize"));
        primaryStage.setTitle(bundle.getString("my.tireShop"));
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
            database.insertToCustomerList(customerNameTextField.getText(),customerSurnameTextField.getText(),Integer.valueOf(customerNumberTextField.getText()));
            database.selectAllCustomerList();
        });
    }

    public static void main( String[] args ) throws SQLException, ClassNotFoundException {

        launch(args);
    }
}