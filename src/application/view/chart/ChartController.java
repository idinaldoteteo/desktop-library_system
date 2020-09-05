package application.view.chart;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import application.controller.JPAUtil;
import application.model.Book;
import application.model.User;
import application.view.main.MainClassController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChartController implements Initializable {
	
    @FXML
    private AnchorPane rootAnchorPane;
        
    @FXML
    private VBox vBoxPane;

    @FXML
    private VBox vBoxPane1;
    
    MainClassController mainClassController = new MainClassController();
    
    @FXML
    void BTClose(ActionEvent event) {
    	((Stage) rootAnchorPane.getScene().getWindow()).close();
    	MainClassController.mainLayout.setEffect(null);
    }
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadBookBarChart();
		loadUserBarChart();
		loadBookPieChart();
		loadUserPieChart();
	}
	
	
	void loadBookPieChart() {
		PieChart bookChart = new PieChart(JPAUtil.getStatisticsQtfyTotalBook());
		bookChart.setTitle("Total of Books");
		bookChart.setLabelsVisible(false);
		vBoxPane1.getChildren().addAll(bookChart);
	}
	
	void loadUserPieChart() {
		PieChart userChart = new PieChart(JPAUtil.getStatisticsQtfyTotalUser());
		userChart.setTitle("Total of Users");
		userChart.setLabelsVisible(false);
		vBoxPane1.getChildren().addAll(userChart);

	}
	void loadBookBarChart() {
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		
		BarChart bookChart = new BarChart(xAxis, yAxis);
		bookChart.setTitle("Performance top 10 Book");
		xAxis.setLabel("Book's name");
		yAxis.setLabel("Units");
		
		XYChart.Series data = new XYChart.Series();
		
    	EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select b, count(i.bookID) " + 
				"from Issue i, Book b " + 
				"where i.bookID = b.number " +
				"group by i.bookID").setMaxResults(10);
		Iterator returnList= query.getResultList().iterator();
		while(returnList.hasNext()) {
			Object[] tuple = (Object[]) returnList.next();
			data.getData().addAll(new XYChart.Data(((Book) tuple[0]).getTitle(), tuple[1]));
		}
		
		bookChart.getData().add(data);
		bookChart.setLegendVisible(false);
		vBoxPane.getChildren().add(bookChart);
	}
	
	void loadUserBarChart() {
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		
		BarChart userChart = new BarChart(xAxis, yAxis);
		userChart.setTitle("Performance top 10 User");
		xAxis.setLabel("User's name");
		yAxis.setLabel("Rent");
		
		XYChart.Series data = new XYChart.Series();
		
    	EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select m, count(i.userID) " + 
				"from Issue i, User m " + 
				"where i.userID = m.userId " +
				"group by i.userID").setMaxResults(10);
		Iterator returnList= query.getResultList().iterator();
		while(returnList.hasNext()) {
			Object[] tuple = (Object[]) returnList.next();
			data.getData().addAll(new XYChart.Data(((User) tuple[0]).getName(), tuple[1]));
		}
		
		userChart.getData().add(data);
		userChart.setLegendVisible(false);
		vBoxPane.getChildren().add(userChart);
	}
	
	
	
	

}
