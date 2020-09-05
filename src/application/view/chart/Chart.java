package application.view.chart;

import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import application.controller.JPAUtil;
import application.model.Book;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Chart extends Application{

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Top 10");
			
			CategoryAxis xAxis = new CategoryAxis();
			xAxis.setLabel("Name");
			NumberAxis yAxis = new NumberAxis();
			yAxis.setLabel("Qtfy");
			
			BarChart barChart = new BarChart(xAxis,yAxis);
			
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
//			
//			data.getData().add(new XYChart.Data("Desktop", 567));
//			data.getData().add(new XYChart.Data("Phone", 150));
//			data.getData().add(new XYChart.Data("Tablet", 63));
			
			barChart.getData().add(data);
			
			VBox vBox = new VBox(barChart);
			
			Scene scene = new Scene(vBox, 600, 400);
			
			primaryStage.setScene(scene);
			primaryStage.setHeight(300);
			primaryStage.setWidth(1200);
			
			primaryStage.show();
			
			
			
			
			
			
//			Parent root = new FXMLLoader().load(getClass().getResource("Chart.fxml"));
//			Scene scene = new Scene(root);
//			primaryStage.setScene(scene);
//			primaryStage.setTitle("Libray Assistant 2018");
//			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
