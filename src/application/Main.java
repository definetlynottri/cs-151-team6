package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.Account;

import java.io.IOException;

public class Main extends Application {

    private static Stage stg;
    private static Account currentAccount;

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
    public static setCurrAcc(Account acc) {
    	this.currentAccount= acc;
    }
    public static Account getCurrAcc() {
    	
    }

    public static void main(String[] args) {
        launch(args);
    }
}
