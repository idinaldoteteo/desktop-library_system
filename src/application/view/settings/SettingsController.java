package application.view.settings;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.view.main.MainClassController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class SettingsController implements Initializable{

	
	@FXML
	private Parent rootPane;
	
    @FXML
    private JFXTextField nDaysUserWithoutFine;

    @FXML
    private JFXTextField finePerDay;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    void BTCancel(ActionEvent event) {
    	Stage stage = (Stage)rootPane.getScene().getWindow();
    	stage.close();
    	MainClassController.mainLayout.setEffect(null);
    }

    @FXML
    void BTSave(ActionEvent event) {
    	int fieldNDays = Integer.parseInt(nDaysUserWithoutFine.getText());
    	float fieldFine = Float.parseFloat(finePerDay.getText());
    	String fieldUser = String.valueOf(username.getText());
    	String fieldPass = String.valueOf(password.getText());
    	
    	try {
    		Preferences preference = new Preferences(fieldNDays, fieldFine, fieldUser, fieldPass);
    		Writer writer = new FileWriter("Config.txt");
			Gson gSon = new Gson();
			gSon.toJson(preference, writer);
			writer.close();
			CreateAlert.alertConfirmation("Settings", "The file config.txt was updated sucessfully!");
		} catch (Exception e) {
			CreateAlert.alertError("Settings", "Fail. Check the information!"); 
		} 
    	
    }
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Preferences preference = Preferences.readConfig();
		nDaysUserWithoutFine.setText(String.valueOf(preference.getnDaysUserWithoutFine()));
		finePerDay.setText(String.valueOf(preference.getFinePerDay()));
		username.setText(String.valueOf(preference.getUsername()));
		password.setText(String.valueOf(preference.getPassword()));
	}

}
