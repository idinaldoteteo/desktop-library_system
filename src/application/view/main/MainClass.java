package application.view.main;

import application.controller.JPAUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainClass extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			new Thread(new Runnable() {
				@Override
				public void run() {
					JPAUtil.getConnect();
				}
			}).start();
			Parent root = new FXMLLoader().load(getClass().getResource("/application/view/main/MainClass.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Libray Assistant 2018");
			Image icon = new Image("application/image/IconSystem.png");
			primaryStage.getIcons().add(icon);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
