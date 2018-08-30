import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Driver extends Application {

	public static final int SCENE_SIZE = 400;
	public static final Paint BACKGROUND = Color.AZURE;

	@Override
	public void start(Stage stage) throws Exception {
		HQAssistant assistant = new HQAssistant();
		Scene scene = new Scene(new Group(), SCENE_SIZE, SCENE_SIZE, BACKGROUND);
		scene.setOnKeyPressed(e -> {
			try {
				assistant.makeGuess();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
