package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.Account;
import objects.Card;
import objects.Course;

import java.io.IOException;

public class Main extends Application {

    private static Stage stg;
    private static Account currentAccount;
    private static Course currentCourse;
    public static Card currentCard;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stg = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }
    public static void setCurrAcc(Account acc) {
    	currentAccount= acc;
    }
    public static Account getCurrAcc() {
    	return currentAccount;
    }
    public static Course getCurrentCourse() {
		return currentCourse;
	}
    public static void setCurrentCourse(Course currentCourse) {
		Main.currentCourse = currentCourse;
	}

    public static void main(String[] args) {
        launch(args);
    }
}
