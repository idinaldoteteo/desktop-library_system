package application.view.toolbar;

import java.net.URL;
import java.util.ResourceBundle;

import application.view.main.MainClassController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ToolbarController implements Initializable{

	MainClassController mainClassController = new MainClassController();
	
    @FXML
    void loadAddBook(ActionEvent event) {
    	mainClassController.loadAddBook();
    }

    @FXML
    void loadAddUser(ActionEvent event) {
    	mainClassController.loadAddUser();
    }

    @FXML
    void loadSettings(ActionEvent event) {
    	mainClassController.loadSettings();
    }

    @FXML
    void loadTableBooks(ActionEvent event) {
    	mainClassController.loadTableBooks();
    }

    @FXML
    void loadTableUsers(ActionEvent event) {
    	mainClassController.loadTableUsers();
    }
	
    @FXML
    void loadChart(ActionEvent event) {
    	mainClassController.loadChart();
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
