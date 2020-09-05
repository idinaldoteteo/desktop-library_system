package application.view.addbook;

import java.net.URL;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import application.controller.JPAUtil;
import application.model.Book;
import application.view.main.MainClassController;
import application.view.settings.CreateAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AddBookController implements Initializable {

	static boolean requestUpdateBook;
	static Book returnListBookController;
	
	String bookTitle;
	String bookNumber;
	String bookAuthor;
	String bookPublisher;
	
	
    @FXML
    private JFXTextField tittle;

    @FXML
    private JFXTextField ID;

	@FXML
    private JFXTextField author;

    @FXML
    private JFXTextField publisher;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private AnchorPane  rootAnchorPane;
    
    @FXML
    private Parent rootStackPane;
    
    public JFXTextField getTittle() {
    	return tittle;
    }
    
    public JFXTextField getAuthor() {
    	return author;
    }
    
    public JFXTextField getPublisher() {
    	return publisher;
    }
    
    public JFXTextField getID() {
    	return ID;
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	JPAUtil.getConnect();
    	initializeReturnBook();
    }
    
    @FXML
    void BTSave() {
    	if(requestUpdateBook) {
    		updateBook();
    	}else {
    		addBook();
    	}
    }

    @FXML
    void BTCancel(ActionEvent event) {
    	this.requestUpdateBook = false; 
    	Stage stage = (Stage) rootAnchorPane.getScene().getWindow();
    	stage.close();
    	MainClassController.mainLayout.setEffect(null);
    }
	
	void initializeReturnBook() {
		if(requestUpdateBook) {
			tittle.setText(returnListBookController.getTitle());
			ID.setText(returnListBookController.getNumber());
			author.setText(returnListBookController.getAuthor());
			publisher.setText(returnListBookController.getTitle());
			ID.setEditable(false);
		}
	}
	
	void addBook() {
    	bookTitle = tittle.textProperty().getValue();
    	bookNumber = ID.textProperty().getValue();
    	bookAuthor = author.textProperty().getValue();
    	bookPublisher = publisher.textProperty().getValue();
    	
    	if(		bookTitle.isEmpty() ||
    			bookAuthor.isEmpty() ||
    			bookNumber.isEmpty() ||
    			bookPublisher.isEmpty()){
    		CreateAlert.jfxAlert(true, "", "Please enter in all fields", "Okay", (StackPane)rootStackPane, rootAnchorPane);
    		return;
    	}
    	
    	Book book = new Book(bookTitle, bookNumber, bookAuthor, bookPublisher, "1");
    	JPAUtil.create(book);
    	CreateAlert.jfxAlert(false, "", "Book issued successfully", "Okay", (StackPane)rootStackPane, rootAnchorPane);
    	tittle.textProperty().set("");
    	ID.textProperty().set("");
    	author.textProperty().set("");
    	publisher.textProperty().set("");
	}
	
	
	public void getBookController(Book book) {
		this.requestUpdateBook = true;
		this.returnListBookController = book;
		MainClassController mainClassController = new MainClassController();
		mainClassController.loadAddBook();
	}
		
	void updateBook() {
    	bookTitle = tittle.textProperty().getValue();
    	bookNumber = ID.textProperty().getValue();
    	bookAuthor = author.textProperty().getValue();
    	bookPublisher = publisher.textProperty().getValue();
		
    	EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		Book book = em.find(Book.class, returnListBookController.getId());
		book.setTitle(bookTitle); 
		book.setNumber(bookNumber); 
		book.setAuthor(bookAuthor); 
		book.setPublisher(bookPublisher); 
		em.getTransaction().commit();
		
		CreateAlert.jfxAlert(false, "", "The book was been updated!", "Okay", (StackPane)rootStackPane, rootAnchorPane);
		((Stage) tittle.getScene().getWindow()).close();
		this.requestUpdateBook = false;
	}

}
