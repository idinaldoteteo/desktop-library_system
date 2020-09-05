package application.view.listuser;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;

import application.controller.JPAUtil;
import application.model.User;
import application.view.adduser.AddUserController;
import application.view.main.MainClassController;
import application.view.settings.CreateAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ListUserController implements Initializable{

	ObservableList<User> list = FXCollections.observableArrayList();
	
	 @FXML
	 private AnchorPane rootAnchorPane;
	 
	 @FXML
	 private Parent rootStackPane;
	 
    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, Integer> col1;

    @FXML
    private TableColumn<User, String> col2;

    @FXML
    private TableColumn<User, String> col3;

    @FXML
    private TableColumn<User, String> col4;

    @FXML
    private TableColumn<User, String> col5;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		JPAUtil.getConnect();
		loadColumn();
		loadTableView();
	}

	private void loadTableView(){
		list.clear();
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("select m from User m");
		List<User> retornaLista = query.getResultList();
		for(User m : retornaLista){
			list.add(new User(m.getId(),
								m.getName(), 
								m.getUserId(), 
								m.getMobile(), 
								m.getEmail())
					);
		}
		
		tableView.setItems(list);
		tableView.setColumnResizePolicy(tableView.CONSTRAINED_RESIZE_POLICY);
		em.close();
	}
	
	void loadColumn() {
		col2.setText("Name");
		col3.setText("User ID");
		col4.setText("Mobile");
		col5.setText("Email");
		
		col2.setCellValueFactory(new PropertyValueFactory<>("name"));
		col3.setCellValueFactory(new PropertyValueFactory<>("userId"));
		col4.setCellValueFactory(new PropertyValueFactory<>("mobile"));
		col5.setCellValueFactory(new PropertyValueFactory<>("email"));
	}
	
	@FXML
	void BTClose() {
		((Stage) rootAnchorPane.getScene().getWindow()).close();
		MainClassController.mainLayout.setEffect(null);
	}
	

    @FXML
    void menDeleteUser() {
    	User selectedUserRow = tableView.getSelectionModel().getSelectedItem();
    	if(selectedUserRow == null) {
    		CreateAlert.jfxAlert(true, "", "User not selected", "Okay", (StackPane)rootStackPane, rootAnchorPane);
    		return;
    	}
    	
		JFXButton yesButton = new JFXButton("Okay");
		yesButton.setOnAction( e ->{
    		Integer id = selectedUserRow.getId();
    		User user = new User();
    		JPAUtil.delete(user, id);
    		list.remove(selectedUserRow);
		});
		
		JFXDialog dailog = CreateAlert.jfxAlertConfirmation(true, yesButton, "",
				"Would like to delete the user " + selectedUserRow.getName() + "?", (StackPane)rootStackPane, rootAnchorPane);
		
		yesButton.addEventHandler(MouseEvent.MOUSE_PRESSED, e ->{
			dailog.close();
		});
    	
    }

    @FXML
    void menuEditUser() {
    	User selectedUserRow = tableView.getSelectionModel().getSelectedItem();
    	if(selectedUserRow == null) {
    		CreateAlert.jfxAlert(true, "", "User not selected", "Okay", (StackPane)rootStackPane, rootAnchorPane);
    		return;
    	}
    	JFXButton yesButton = new JFXButton("Okay");
		yesButton.setOnAction( e ->{
    		AddUserController userController = new AddUserController();
    		userController.getController(selectedUserRow);
    		loadTableView();
		});
		
		JFXDialog dailog = CreateAlert.jfxAlertConfirmation(true, yesButton, "",
				"Would like to edit the user " + selectedUserRow.getName() + "?", (StackPane)rootStackPane, rootAnchorPane);
		
		yesButton.addEventHandler(MouseEvent.MOUSE_PRESSED, e ->{
			dailog.close();
		});
    	
    }

    @FXML
    void menuRefreshUser() {
    	loadTableView();
    }
	
    
}
