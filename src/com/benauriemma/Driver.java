package com.benauriemma;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * Main class to start application
 * Uses JavaFX to react to key press
 * 
 * @author benauriemma
 *
 */
public class Driver extends Application {

	public static final int SCENE_SIZE = 400;
	public static final Paint BACKGROUND = Color.AZURE;

	@Override
	public void start(Stage stage) throws Exception {
		HQAssistant assistant = new HQAssistant();
		Scene scene = new Scene(new Group(), SCENE_SIZE, SCENE_SIZE, BACKGROUND);
		scene.setOnKeyPressed(e -> {
			try {
				assistant.assist();
			} catch (IOException e1) {
				e1.printStackTrace();
				// TODO: handle this exception more responsibly
			}
		});
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
