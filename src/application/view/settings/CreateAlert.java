package application.view.settings;

import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class CreateAlert {
		
	public static JFXDialog jfxAlertConfirmation(Boolean screenBlur, JFXButton buttonYes, String title, 
			String textBody, Node primaryStackPane, Node secondBorderPane ) {
		BoxBlur blur = new BoxBlur(3,3,3);
		JFXDialogLayout dailogLayout = new JFXDialogLayout();
		JFXDialog dailog = new JFXDialog((StackPane) primaryStackPane, dailogLayout, DialogTransition.TOP);

		dailogLayout.setHeading(new Label(title));
		dailogLayout.setBody(new Text(textBody));
		JFXButton cancelButton = new JFXButton("Cancel");
		cancelButton.setOnAction(e -> {
			dailog.close();
	    });
		HBox hBox = new HBox(5);
	    hBox.getChildren().addAll(buttonYes, cancelButton);
		dailogLayout.setActions(hBox);
		dailog.show();
		return dailog;
	}
	
	
	public static void jfxAlert(Boolean screenBlur, String title, String textBody, 
			String infoButton, StackPane primaryStackPane, Node secondBorderPane ) {
		BoxBlur blur = new BoxBlur(3,3,3);
		JFXDialogLayout dailogLayout = new JFXDialogLayout();
		JFXButton button = new JFXButton(infoButton);
		JFXDialog dailog = new JFXDialog(primaryStackPane, dailogLayout, DialogTransition.TOP);
		button.setOnAction(e -> {
			dailog.close();
	    });	
		dailogLayout.setHeading(new Label(title));
		dailogLayout.setBody(new Text(textBody));
		dailogLayout.setActions(button);
		dailog.show();
		if(screenBlur)
			secondBorderPane.setEffect(blur);
		dailog.setOnDialogClosed(e ->{
			secondBorderPane.setEffect(null);
		});

	}
	
	
	public static void alertError(String title, String contentText) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
	
	public static void alertInformation(String title, String contentText) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
	
	public static String alertConfirmation(String title, String contentText) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(contentText);
		Optional<ButtonType> response = alert.showAndWait();
		if(response.get() == ButtonType.OK) {
			return "OK";
		}else {
			return "CANCEL";
		}
	}

}
