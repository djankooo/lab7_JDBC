package Models;

import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.util.Locale;
import java.util.ResourceBundle;

public class Language {

    private ResourceBundle bundle = ResourceBundle.getBundle("LabelsBundle", new Locale("pl", "PL"));
    private Locale selectedLocale = null;
    private ResourceBundle selectedBundle = bundle;

    public ToggleGroup getRadioGroup() {
        return radioGroup;
    }

    public void setRadioGroup(ToggleGroup radioGroup) {
        this.radioGroup = radioGroup;
    }

    private ToggleGroup radioGroup = new ToggleGroup();

    public ResourceBundle getBundle() {
        return bundle;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public Locale getSelectedLocale() {
        return selectedLocale;
    }

    public void setSelectedLocale(Locale selectedLocale) {
        this.selectedLocale = selectedLocale;
    }

    public ResourceBundle getSelectedBundle() {
        return selectedBundle;
    }

    public void setSelectedBundle(ResourceBundle selectedBundle) {
        this.selectedBundle = selectedBundle;
    }

    public VBox languageSelectors(){

        VBox languageVBox = new VBox();
        RadioButton ENradioButton = new RadioButton();
        RadioButton PLradioButton = new RadioButton();

        PLradioButton.setGraphic(new ImageView(new Image("https://cdn2.iconfinder.com/data/icons/flags_gosquared/64/Poland.png")));
        ENradioButton.setGraphic(new ImageView(new Image("https://cdn2.iconfinder.com/data/icons/flags_gosquared/64/United-Kingdom.png")));

        PLradioButton.setUserData(new Locale("pl", "PL"));
        ENradioButton.setUserData(new Locale("en", "US"));
        PLradioButton.setToggleGroup(radioGroup);
        ENradioButton.setToggleGroup(radioGroup);
        PLradioButton.fire();
        languageVBox.setAlignment(Pos.TOP_RIGHT);
        languageVBox.getChildren().addAll(ENradioButton, PLradioButton);

        return languageVBox;
    }
}
