package application.view.main;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import application.controller.JPAUtil;
import application.model.Book;
import application.model.Issue;
import application.model.User;
import application.model.RegistroMovimentacao;
import application.view.settings.CreateAlert;
import application.view.main.MainClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainClassController implements Initializable {

	ObservableList<Object> list = FXCollections.observableArrayList();
	int returnSearchBookId = 0;
	int returnSearchIdIssue = 0;
	boolean returnSearchBook;
	boolean returnSearchUser;

	String returnSearchBookStatus = "0";
	LocalDateTime returnSearchTimeSubmissio = null;
	public static Stage mainStage;
	public static StackPane mainLayout;
	public static Parent secondLayout;
	private JFXDialog dailog;

	@FXML
	private BorderPane rootBorderPane;

	@FXML
	private Parent rootPane;

	@FXML
	private HBox containerRenewSubmission;

	@FXML
	private HBox book_info;

	@FXML
	private HBox user_info;

	@FXML
	private TextField fieldBookInput;

	@FXML
	private Text fieldBookName;

	@FXML
	private Text fiedlBookAuthor;

	@FXML
	private Text fieldBookStatus;

	@FXML
	private TextField fieldUserInput;

	@FXML
	private Text fiedlUserName;

	@FXML
	private Text fieldUserContact;

	@FXML
	private JFXTextField fieldRenewInput;

	@FXML
	private ListView<Object> listView;

	@FXML
	private JFXHamburger BTHamburger;

	@FXML
	private JFXDrawer drawer;

	@FXML
	private JFXListView<String> listViewBook;

	@FXML
	private JFXListView<String> listViewUser;

	@FXML
	private JFXListView<String> listViewIssue;

	@FXML
	private JFXButton BTRenew;

	@FXML
	private JFXButton BTSubmission;

	@FXML
	private StackPane containerBook;

	@FXML
	private StackPane containerUser;

	private static PieChart bookChart;
	private static PieChart userChart;

	public PieChart getBookPieChart() {
		return bookChart;
	}

	public PieChart getUserPieChart() {
		return userChart;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		JFXDepthManager.setDepth(book_info, 1);
		JFXDepthManager.setDepth(user_info, 1);
		initDrawer();
		initGraphs();
	}

	public void initGraphs() {
		bookChart = new PieChart(JPAUtil.getStatisticsQtfyTotalBook());
		bookChart.setLabelsVisible(true);
		bookChart.setLegendVisible(false);
		bookChart.setLabelLineLength(10);
		containerBook.getChildren().addAll(bookChart);
		
		
		userChart = new PieChart(JPAUtil.getStatisticsQtfyTotalUser());
		userChart.setLabelsVisible(true); userChart.setLegendVisible(false);
		userChart.setLabelLineLength(10);
		containerUser.getChildren().addAll(userChart);
		 
	}

	private void initDrawer() {
		try {
			VBox toolbar = FXMLLoader.load(getClass().getResource("/application/view/toolbar/Toolbar.fxml"));
			drawer.setSidePane(toolbar);
		} catch (IOException e) {
			e.printStackTrace();
		}

		HamburgerSlideCloseTransition burgerTask = new HamburgerSlideCloseTransition(BTHamburger);
		burgerTask.setRate(-1);
		BTHamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
			burgerTask.setRate(burgerTask.getRate() * -1);
			burgerTask.play();
			if (drawer.isShown()) {
				drawer.close();
				drawer.setMinWidth(0);
			} else {
				drawer.open();
				drawer.setMinWidth(160);
			}

		});

	}

	@FXML
	public void loadAddBook() {
		loadWindows("/application/view/addbook/AddBook.fxml", "Add new Book", "/application/image/AddBook.png");
	}

	@FXML
	public void loadAddUser() {
		loadWindows("/application/view/adduser/AddUser.fxml", "Add new User", "/application/image/AddUser.png");
	}

	@FXML
	public void loadTableBooks() {
		loadWindows("/application/view/listbook/ListBook.fxml", "Table View all Books",
				"/application/image/ViewBook.png");
	}

	@FXML
	public void loadTableUsers() {
		loadWindows("/application/view/listuser/ListUser.fxml", "Table View all Users",
				"/application/image/View.png");
	}

	@FXML
	public void loadChart() {
		loadWindows("/application/view/chart/Chart.fxml", "Statistical Information", "/application/image/Setting.png");
	}

	@FXML
	public void loadSettings() {
		loadWindows("/application/view/settings/Settings.fxml", "Settings", "/application/image/Setting.png");
	}

	void loadWindows(String loc, String title, String icon) {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainClass.class.getResource(loc));
			if (mainStage == null) {
				mainStage = new Stage(StageStyle.UNDECORATED);
				mainLayout = loader.load();
				mainStage.setScene(new Scene(mainLayout));
				mainStage.setTitle(title);
				Image iconSystem = new Image(icon);
				mainStage.getIcons().add(iconSystem);
				mainStage.show();

			} else {
				BoxBlur blur = new BoxBlur(3, 3, 3);
				Parent secondLayout = loader.load();
				Stage otherStage = new Stage(StageStyle.UNDECORATED);
				otherStage.initModality(Modality.WINDOW_MODAL);
				otherStage.initOwner(mainStage);
				otherStage.setScene(new Scene(secondLayout));
				mainLayout.setEffect(blur);
				otherStage.setTitle(title);
				Image iconSystem = new Image(icon);
				otherStage.getIcons().add(iconSystem);
				otherStage.showAndWait();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadMain() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				JPAUtil.getConnect();
			}
		}).start();
		loadWindows("/application/view/main/MainClass.fxml", "Libray Assistant 2018",
				"application/image/IconSystem.png");
	}

	void clearBookInformation() {
		fieldBookName.setText("");
		fiedlBookAuthor.setText("");
		fieldBookStatus.setText("");
	}

	void clearUserInformation() {
		fiedlUserName.setText("");
		fieldUserContact.setText("");
	}

	@FXML
	void BTActionClean() {
		cleanAndUpdateChart();
//    	bookChart.setOpacity(1);
//    	userChart.setOpacity(1);
		clearBookInformation();
		fieldBookInput.setText("");
		clearUserInformation();
		fieldUserInput.setText("");
	}

	void cleanAndUpdateChart() {
		containerBook.getChildren().remove(bookChart);
		containerUser.getChildren().remove(userChart);
		initGraphs();
	}

	@FXML
	void inputTab1BookID(ActionEvent event) {
		EntityManager em = JPAUtil.getEntityManager();
		boolean flag = false;
		returnSearchBookId = 0;
		returnSearchBook = false;
		Query query = em.createQuery("select X from Book X where Number ='" + fieldBookInput.getText() + "'");
		List<Book> resultado = query.getResultList();
		for (Book b : resultado) {
			returnSearchBookId = b.getId();
			fieldBookName.setText("Book Name:" + b.getTitle());
			fiedlBookAuthor.setText("author: " + b.getAuthor());
			fieldBookStatus.setText("status: " + (b.getAvailable().equals("1") ? "Available" : "Not Available"));
			returnSearchBookStatus = b.getAvailable();
			flag = true;
		}
		if (!flag) {
			clearBookInformation();
			fieldBookName.setText("Book not found");
		}
		returnSearchBook = flag;
		em.close();
		containerBook.getChildren().remove(bookChart);
	}

	@FXML
	void inputTab1UserID(ActionEvent event) {
		EntityManager em = JPAUtil.getEntityManager();
		boolean flag = false;
		returnSearchUser = false;
		Query query = em.createQuery("select X from User X where UserID ='" + fieldUserInput.getText() + "'");
		List<User> resultado = query.getResultList();
		for (User b : resultado) {
			fiedlUserName.setText("User name: " + b.getName());
			fieldUserContact.setText("contact: " + b.getMobile());
			flag = true;
		}
		if (!flag) {
			clearUserInformation();
			fiedlUserName.setText("User not found");
		}
		returnSearchUser = flag;
		em.close();
		containerUser.getChildren().remove(userChart);
	}

	@FXML
	void BTActionIssue(ActionEvent event) {
		if (fieldBookInput.getText().isEmpty() || fieldUserInput.getText().isEmpty()) {
			CreateAlert.jfxAlert(true, "", "Please, complete all information", "Okay", (StackPane) rootPane,
					rootBorderPane);
			return;
		} else if (!returnSearchBook || returnSearchBookStatus.equals("0")) {
			CreateAlert.jfxAlert(true, "", "Book " + fieldBookInput.getText() + " it is not available", "Okay",
					(StackPane) rootPane, rootBorderPane);
			return;
		} else if (!returnSearchUser) {
			CreateAlert.jfxAlert(true, "", "User " + fieldUserInput.getText() + " it is not available", "Okay",
					(StackPane) rootPane, rootBorderPane);
			return;
		}

		JPAUtil.getConnect();
		String bookID = fieldBookInput.textProperty().getValue();
		String userID = fieldUserInput.textProperty().getValue();
		LocalDateTime time = LocalDateTime.now();

		Issue issue = new Issue(bookID, userID, time);

		JFXButton yesButton = new JFXButton("Okay");
		yesButton.setOnAction(e -> {
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			Book book = em.find(Book.class, returnSearchBookId);
			book.setAvailable("0");
			em.getTransaction().commit();

			JPAUtil.create(issue);
			CreateAlert.jfxAlert(false, "", "Done!", "Okay", (StackPane) rootPane, rootBorderPane);
			em.close();
			dailog.close();
			BTActionClean();
		});

		dailog = CreateAlert.jfxAlertConfirmation(true, yesButton, "", "Would you like to continue issue?\n\t"
				+ fieldBookName.getText() + "\n\tto " + fiedlUserName.getText() + " ?", (StackPane) rootPane,
				rootBorderPane);

		JPAUtil.getStatisticsQtfyTotalBook();
		JPAUtil.getStatisticsQtfyTotalUser();

	}

	void clearInformationTab2() {
		listViewIssue.getItems().clear();
		listViewBook.getItems().clear();
		listViewUser.getItems().clear();
		containerRenewSubmission.setOpacity(0);
		BTRenew.setDisable(true);
		BTSubmission.setDisable(true);
	}

	@FXML
	void inputTab2BookID(ActionEvent event) {
		returnSearchBookId = 0;
		clearInformationTab2();

		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("select X from Issue X where Book_ID='" + fieldRenewInput.getText() + "'");
		List<Issue> retornoListaIssue = query.getResultList();
		if (retornoListaIssue.size() == 0) {
			CreateAlert.jfxAlert(true, "", "Book not rent!", "Okay", (StackPane) rootPane, rootBorderPane);
			return;
		}
		String returnNomeUser = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-y H:m");
		for (Issue b : retornoListaIssue) {
			returnNomeUser = b.getUserID();
			returnSearchIdIssue = b.getId();
			listViewIssue.getItems().addAll("Time: " + formatter.format(b.getTimeIssue()),
					"Days rented: " + (ChronoUnit.DAYS.between(b.getTimeIssue(), LocalDateTime.now())));
		}
		listViewIssue.depthProperty().set(3);
		listViewIssue.setExpanded(false);

		query = em.createQuery("select X from Book X where Number='" + fieldRenewInput.getText() + "'");
		List<Book> retornoListaBook = query.getResultList();
		for (Book b : retornoListaBook) {
			returnSearchBookId = b.getId();

			listViewBook.getItems().addAll("Title: " + b.getTitle(), "ID: " + b.getNumber(), "Author: " + b.getAuthor(),
					"Publisher: " + b.getPublisher());
		}
		listViewBook.depthProperty().set(3);
		listViewBook.setExpanded(false);

		query = em.createQuery("select m from User m where UserID ='" + returnNomeUser + "'");
		List<User> retornoListaUser = query.getResultList();
		for (User b : retornoListaUser) {
			listViewUser.getItems().addAll("Name: " + b.getName(), "ID: " + b.getUserId(),
					"Mobile: " + b.getMobile(), "Email: " + b.getEmail());
		}
		listViewUser.depthProperty().set(3);
		listViewUser.setExpanded(false);
		containerRenewSubmission.setOpacity(1);
		BTRenew.setDisable(false);
		BTSubmission.setDisable(false);
		em.close();
	}

	@FXML
	void BTActionRenew(ActionEvent event) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		Issue issue = em.find(Issue.class, returnSearchIdIssue);
		JFXButton yesButton = new JFXButton("Okay");
		yesButton.setOnAction(e -> {
			RegistroMovimentacao registro = new RegistroMovimentacao(issue.getBookID(), issue.getUserID(),
					issue.getTimeIssue(), LocalDateTime.now());

			em.persist(registro);
			issue.setTimeIssue(LocalDateTime.now());
			em.getTransaction().commit();
			clearInformationTab2();
			CreateAlert.jfxAlert(false, "", "Done!", "Okay", (StackPane) rootPane, rootBorderPane);
			em.close();
			dailog.close();
		});
		dailog = CreateAlert.jfxAlertConfirmation(true, yesButton, "", "Would you like to confirm renew of Book? ",
				(StackPane) rootPane, rootBorderPane);

	}

	@FXML
	void BTActionSubmission(ActionEvent event) {
		if (returnSearchIdIssue != 0) {
			JFXButton yesButton = new JFXButton("Okay");
			yesButton.setOnAction(e -> {
				EntityManager em = JPAUtil.getEntityManager();
				em.getTransaction().begin();
				Book book = em.find(Book.class, returnSearchBookId);
				book.setAvailable("1");
				Issue issue = em.find(Issue.class, returnSearchIdIssue);
				RegistroMovimentacao registro = new RegistroMovimentacao(issue.getBookID(), issue.getUserID(),
						issue.getTimeIssue(), LocalDateTime.now());
				em.persist(registro);
				em.remove(issue);
				em.getTransaction().commit();
				clearInformationTab2();
				CreateAlert.jfxAlert(false, "", "Done!", "Okay", (StackPane) rootPane, rootBorderPane);
				em.close();
				dailog.close();
				cleanAndUpdateChart();
			});

			dailog = CreateAlert.jfxAlertConfirmation(true, yesButton, "",
					"Would you like to confirm the submission of Book? ", (StackPane) rootPane, rootBorderPane);

		} else {
			CreateAlert.jfxAlert(true, "", "Fail", "Okay", (StackPane) rootPane, rootBorderPane);
		}

	}

	@FXML
	void menuAddBook() {
		loadAddBook();
	}

	@FXML
	void menuAddUser() {
		loadAddUser();
	}

	@FXML
	void menuFileClose() {
		JPAUtil.getDisconect();
		((Stage) rootPane.getScene().getWindow()).close();
	}

	@FXML
	void menuListTableBook() {
		loadTableBooks();
	}

	@FXML
	void menuListTableUser() {
		loadTableUsers();
	}

	@FXML
	void menuFullScreen() {
		Stage stage = (Stage) rootPane.getScene().getWindow();
		stage.setFullScreen(!stage.isFullScreen());
	}

}
