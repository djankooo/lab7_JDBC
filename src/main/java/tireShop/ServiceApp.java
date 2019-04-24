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

public class ServiceApp extends Application {

    Language language = new Language();

    Button addNewServiceButton = new Button();
    Button summaryMonthButton = new Button();

    VBox bodyVBox = new VBox();

    private void setUIControls(final GridPane pane, final Stage primaryStage) throws Exception {

        renameLabels(language.getSelectedBundle(), primaryStage);

        addNewServiceButton.setPrefSize(200,40);
        summaryMonthButton.setPrefSize(200,40);

        bodyVBox.getChildren().addAll(addNewServiceButton,summaryMonthButton);
        pane.add(bodyVBox,3,3);

    }

    private void renameLabels(ResourceBundle bundle, Stage primaryStage){
        addNewServiceButton.setText(bundle.getString("my.addNewService"));
        summaryMonthButton.setText(bundle.getString("my.summarize"));
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

        addNewServiceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    //addClick();
                    Stage addingStage = new Stage();
                    ServiceAdd add = new ServiceAdd(language);
                    add.start(addingStage);
                    addingStage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        summaryMonthButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    //addClick();
                    Stage addingStage = new Stage();
                    ServiceSummaryMonth add = new ServiceSummaryMonth(language);
                    add.start(addingStage);
                    addingStage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main( String[] args ) throws SQLException, ClassNotFoundException {
        Database d = new Database();
        launch(args);
    }
}
