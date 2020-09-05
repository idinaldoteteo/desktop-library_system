package application.view.listbook;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;

import application.controller.JPAUtil;
import application.model.Book;
import application.view.addbook.AddBook;
import application.view.addbook.AddBookController;
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

public class ListBookController implements Initializable {
	
	ObservableList<Book> list = FXCollections.observableArrayList();
	MainClassController mainClassController;
	
	
	 	@FXML
	    private AnchorPane rootAnchorPane;
	 	
	 	@FXML
	    private Parent rootStackPane;

	    @FXML
	    private TableView<Book> tableView;
	    
	    @FXML
	    private TableColumn<Book, Integer> col1;

	    @FXML
	    private TableColumn<Book, String> col2;

	    @FXML
	    private TableColumn<Book, String> col3;

	    @FXML
	    private TableColumn<Book, String> col4;

	    @FXML
	    private TableColumn<Book, String> col5;

	    @FXML
	    private TableColumn<Book, String> col6;

	    
		public ObservableList<Book> getList() {
			return list;
		}

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			JPAUtil.getConnect();
			loadColum();
			loadTableView();
		}
	
		public void loadTableView(){
			list.clear();
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("select b from Book b");
			List<Book> retornaLista = query.getResultList();
			for(Book b : retornaLista){
				list.add(new Book(b.getId(),
							b.getTitle(), 
							b.getNumber(), 
							b.getAuthor(), 
							b.getPublisher(), 
							(b.getAvailable().equals( "1")) ?"Yes":"Not")
						);
			}
			
			tableView.setItems(list);
			tableView.setColumnResizePolicy(tableView.CONSTRAINED_RESIZE_POLICY);
			em.close();
		}
		
		private void loadColum(){
			col2.setText("Title");
			col3.setText("Number ID");
			col4.setText("Author");
			col5.setText("Publisher");
			col6.setText("Available");
			
			col2.setCellValueFactory(new PropertyValueFactory<>("title"));
			col3.setCellValueFactory(new PropertyValueFactory<>("number"));
			col4.setCellValueFactory(new PropertyValueFactory<>("author"));
			col5.setCellValueFactory(new PropertyValueFactory<>("publisher"));
			col6.setCellValueFactory(new PropertyValueFactory<>("available"));
		}
		
		@FXML
		void BTClose() {
			((Stage) rootAnchorPane.getScene().getWindow()).close();
			MainClassController.mainLayout.setEffect(null);
		}
		
		@FXML
	    void menuDeleteRow() {
			Book selectedDeleteRow = tableView.getSelectionModel().getSelectedItem();
			if(selectedDeleteRow == null) {
				CreateAlert.jfxAlert(true, "", "No Book selected", "Okay", (StackPane)rootStackPane, rootAnchorPane);
				return;
			}else if (selectedDeleteRow.getAvailable().equals("Not")){
				CreateAlert.jfxAlert(true, "", "This Book is have been Issued, so you can't delete this row", 
						"Okay", (StackPane)rootStackPane, rootAnchorPane);
				return;
			}
			
			JFXButton yesButton = new JFXButton("Okay");
			yesButton.setOnAction( e ->{
				if(selectedDeleteRow.getAvailable().equals("Yes")) {
					Integer id = selectedDeleteRow.getId();
					Book book = new Book();
					JPAUtil.delete(book, id);
					list.remove(selectedDeleteRow);
				}
			});
			
			JFXDialog dailog = CreateAlert.jfxAlertConfirmation(true, yesButton, "",
					"Would like to delete the book " + selectedDeleteRow.getAuthor() + "?", (StackPane)rootStackPane, rootAnchorPane);
			
			yesButton.addEventHandler(MouseEvent.MOUSE_PRESSED, e ->{
				dailog.close();
			});
			
	    }
		
		@FXML
		void menuEditRow() {
			Book selectedEditRow = tableView.getSelectionModel().getSelectedItem();
			if(selectedEditRow == null) {
				CreateAlert.jfxAlert(true, "", "No Book selected","Okay", (StackPane)rootStackPane, rootAnchorPane);
				return;
			}
			JFXButton yesButton = new JFXButton("Okay");
			yesButton.setOnAction( e ->{
				AddBookController bookController = new AddBookController();
				bookController.getBookController(selectedEditRow);
				loadTableView();

			});
			
			JFXDialog dailog = CreateAlert.jfxAlertConfirmation(true, yesButton, "",
					"Would like to edit the book " + selectedEditRow.getAuthor() + "?", (StackPane)rootStackPane, rootAnchorPane);
			
			yesButton.addEventHandler(MouseEvent.MOUSE_PRESSED, e ->{
				dailog.close();
			});
			
		}
		
		@FXML
		void menuRefresh() {
			loadTableView();
		}
		
}
