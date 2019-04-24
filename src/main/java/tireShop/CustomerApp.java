package tireShop;

import Models.Database;
import Models.Language;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class CustomerApp extends Application {

    Language language = new Language();

    Button addNewCustomerButton = new Button();
    Button signUpButton = new Button();

    VBox bodyVBox = new VBox();

    private void setUIControls(final GridPane pane, final Stage primaryStage) throws Exception {

        renameLabels(language.getSelectedBundle(), primaryStage);

        addNewCustomerButton.setPrefSize(200,40);
        signUpButton.setPrefSize(200,40);

        bodyVBox.getChildren().addAll(addNewCustomerButton, signUpButton);
        pane.add(bodyVBox,3,3);

    }

    private void renameLabels(ResourceBundle bundle, Stage primaryStage){
        addNewCustomerButton.setText(bundle.getString("my.newUser"));
        signUpButton.setText(bundle.getString("my.signUp"));
        primaryStage.setTitle(bundle.getString("my.tireShop"));
    }

    private GridPane createPane() {

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setHgap(10);
        pane.setVgap(10);
        return pane;
    }
    @Override
    public void start(final Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();
        GridPane gridPane = createPane();

        borderPane.setCenter(gridPane);
        borderPane.setTop(language.languageSelectors());

        setUIControls(gridPane, primaryStage);
        primaryStage.setScene(new Scene(borderPane, 1000, 500));
        primaryStage.show();

        language.getRadioGroup().selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (language.getRadioGroup().getSelectedToggle() != null) {
                    language.setSelectedLocale((Locale) language.getRadioGroup().getSelectedToggle().getUserData());
                    language.setSelectedBundle(ResourceBundle.getBundle("LabelsBundle", language.getSelectedLocale()));
                    renameLabels(language.getSelectedBundle(), primaryStage);
                }
            }
        });

        addNewCustomerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    //addClick();
                    Stage addingStage = new Stage();
                    CustomerAdd add = new CustomerAdd(language);
                    add.start(addingStage);
                    addingStage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        signUpButton.setOnAction(event -> {
            try {
                //addClick();
                Stage addingStage = new Stage();
                CustomerSignUp add = new CustomerSignUp(language);
                add.start(addingStage);
                addingStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main( String[] args ) throws SQLException, ClassNotFoundException {
        Database d = new Database();
        launch(args);
    }
}
