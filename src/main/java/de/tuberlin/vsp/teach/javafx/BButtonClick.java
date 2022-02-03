package de.tuberlin.vsp.teach.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

class BButtonClick extends Application {
	public static void main(String[] args) { launch(args); }
	@Override
	public void start( Stage primaryStage ) {
		primaryStage.setTitle("Halloooooo");
		Button button = new Button( "Klick mich" );
		button.setOnAction(new MyButtonEventHandler() ) ;
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		Scene scene = new Scene(layout, 300, 200);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	private static class MyButtonEventHandler implements EventHandler<ActionEvent>{
		@Override public void handle( ActionEvent event ) {
			System.out.println("Hello World!");
		}
	}
}
