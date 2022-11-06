package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

//show success message and button to go back to login page
public class success {

    @FXML
    private Button loginPage;
    //go to loginpage 
    public void gotoLogin(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("sample.fxml");
    }
}
