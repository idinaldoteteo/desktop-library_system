package application.view.adduser;

import java.net.URL;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import application.controller.JPAUtil;
import application.model.Book;
import application.model.User;
import application.view.main.MainClassController;
import application.view.settings.CreateAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AddUserController implements Initializable{

	static boolean requestUpdateUser;
	static User returnListUserController;
	
	String userName;
	String userUserId;
	String userMobile;
	String userEmail;
	
    @FXML
    private AnchorPane rootAnchorPane;
    
    @FXML
    private Parent rootStackPane;
    
    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField userId;

    @FXML
    private JFXTextField mobile;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton cancelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	JPAUtil.getConnect();
    	if(requestUpdateUser) {
    		name.setText(returnListUserController.getName());
    		userId.setText(returnListUserController.getUserId());
    		mobile.setText(returnListUserController.getMobile());
    		email.setText(returnListUserController.getEmail());
    		userId.setEditable(false);
    	}
    }
    
    @FXML
    void BTCancel(ActionEvent event) {
    	Stage stage = (Stage) rootAnchorPane.getScene().getWindow();
    	stage.close();
    	this.requestUpdateUser = false;
    	MainClassController.mainLayout.setEffect(null);
    }

    @FXML
    void BTSave() {
    	if(requestUpdateUser) {
    		updateUser();
    	}else {
    		addUser();
    	}
    }
    
    public void getController(User user) {
    	this.requestUpdateUser = true;
    	this.returnListUserController = user;
		MainClassController mainClassController = new MainClassController();
		mainClassController.loadAddUser();
    	
    }
    
    void updateUser() {
    	userName =  name.textProperty().getValue();
    	userUserId =  userId.textProperty().getValue();
    	userMobile=  mobile.textProperty().getValue();
    	userEmail=  email.textProperty().getValue();
    			
    	EntityManager em = JPAUtil.getEntityManager();
    	em.getTransaction().begin();
    	User user = em.find(User.class, returnListUserController.getId());
    	user.setName(userName);
    	user.setUserId(userUserId);
    	user.setMobile(userMobile);
    	user.setEmail(userEmail);
    	em.getTransaction().commit();
    	
    	CreateAlert.jfxAlert(false, "", "Book issued successfully", "Okay", (StackPane)rootStackPane, rootAnchorPane);
    	((Stage) name.getScene().getWindow()).close();
    	this.requestUpdateUser = false;
    			
    }
    
    void addUser() {
    	userName =  name.textProperty().getValue();
    	userUserId =  userId.textProperty().getValue();
    	userMobile=  mobile.textProperty().getValue();
    	userEmail=  email.textProperty().getValue();
    	
    	if( 	userName.isEmpty() ||
    			userUserId.isEmpty() ||
    			userMobile.isEmpty() ||
    			userEmail.isEmpty()){
    		CreateAlert.jfxAlert(true, "", "Please enter in all fields", "Okay", (StackPane)rootStackPane, rootAnchorPane);
    		return;
    	}
    	
    	User user = new User(userName, userUserId, userMobile, userEmail);
    	JPAUtil.create(user);
    	CreateAlert.jfxAlert(false, "", "User issued successfully", "Okay", (StackPane)rootStackPane, rootAnchorPane);
    	name.textProperty().set("");
    	userId.textProperty().set("");
    	mobile.textProperty().set("");
    	email.textProperty().set("");
    }
	


}
