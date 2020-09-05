package application.view.settings;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.codec.digest.DigestUtils;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.controller.JPAUtil;
import application.view.main.MainClass;
import application.view.main.MainClassController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable{

	Preferences preference;
	
    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private Text alertErrorLogin;
    
    @FXML
    private Parent rootPane;

    @FXML
    void BTCancel(ActionEvent event) {
    	System.exit(0);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		preference.createConfig();
		txtUsername.requestFocus();
	}
	
    @FXML
    void BTLogin(ActionEvent event) {
    		String user = txtUsername.getText();
    		String pass = DigestUtils.shaHex(txtPassword.getText());
   		
    		if( user.equals(preference.readConfig().getUsername()) &&
        		pass.equals(preference.readConfig().getPassword())
        	   ) {
        		closeStage();
        		loadMain();
        			
        	}else {
        		createAlertMensagemLogin();
        		}
    		
    }
    
    @FXML
    void clickMouseIntoUsername(MouseEvent event) {
    	cleanCreateAlertMensagemLogin();
    }
    
    void createAlertMensagemLogin() {
		alertErrorLogin.setText("Username or Password invalid!");
		txtUsername.getStyleClass().add("wrong-login");
		txtPassword.getStyleClass().add("wrong-login");
    }

    void cleanCreateAlertMensagemLogin() {
		alertErrorLogin.setText("");
		txtUsername.getStyleClass().remove("wrong-login");
		txtPassword.getStyleClass().remove("wrong-login");
    }

    
    void closeStage() {
    	((Stage) rootPane.getScene().getWindow()).close();

    }
    
    void loadMain(){
    	MainClassController mainClassController = new MainClassController();
    	mainClassController.loadMain();
    }
	
}
