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

public class ServiceAdd extends Application {

    Language language;
    Database database = new Database();

    Label serviceNameLabel = new Label();
    Label servicePriceLabel = new Label();
    TextField serviceNameTextField = new TextField();
    TextField servicePriceTextField = new TextField();

    Button submitButton = new Button("Add!");

    public ServiceAdd(Language language) throws SQLException, ClassNotFoundException {
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

        pane.add(serviceNameLabel,0,0);
        pane.add(servicePriceLabel,0,1);
        pane.add(serviceNameTextField,1,0);
        pane.add(servicePriceTextField,1,1);
        pane.add(submitButton,1,2);

        pane.setHalignment(submitButton, HPos.RIGHT);
    }

    private void renameLabels(ResourceBundle bundle, Stage primaryStage){
        serviceNameLabel.setText(bundle.getString("my.service"));
        servicePriceLabel.setText(bundle.getString("my.servicePrice"));
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
            database.insertToServicesList(serviceNameTextField.getText(),Double.valueOf(servicePriceTextField.getText()));
            database.selectAllServicesList();
        });
    }

    public static void main( String[] args ) throws SQLException, ClassNotFoundException {

        launch(args);
    }
}
