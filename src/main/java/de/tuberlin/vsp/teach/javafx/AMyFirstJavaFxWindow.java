package de.tuberlin.vsp.teach.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

class AMyFirstJavaFxWindow extends Application{

	public static void main(String[] args) { launch(args); }
	@Override
	public void start( Stage primaryStage ) throws Exception {
		primaryStage.setTitle("Mein erstes Fenster" );
		Button button = new Button("Klick mich");
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		Scene scene = new Scene(layout, 300, 200);
		primaryStage.setScene(scene );
		primaryStage.show();
	}
}
