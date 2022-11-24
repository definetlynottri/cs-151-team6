package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

//shows success message after successful login
public class AfterLogin {

    @FXML
    private Button mainpage;
    //lead to mainpage 
    public void gotoMainpage(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("CourseBrowser.fxml");
    }
}
