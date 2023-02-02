package de.tuberlin.vsp.teach.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class AMyFirstJavaFxWindow extends Application{

	@Override
	public void start( Stage primaryStage ) throws Exception{

		primaryStage.setTitle( "Mein erstes Fenster" );


		Button button = new Button( "Klick mich" );

		button.setOnAction( abc -> System.out.println( "button clicked" ) );

		StackPane layout = new StackPane();
		layout.getChildren().add( button );

		Scene scene = new Scene( layout, 300, 200 );
		primaryStage.setScene( scene );

		primaryStage.show();
	}

}
