package application.view.settings;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Setting extends Application{

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = new FXMLLoader().load(getClass().getResource("Settings.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Libray Assistant 2018");
			Image icon = new Image("application/image/Setting.png");
			primaryStage.getIcons().add(icon);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
