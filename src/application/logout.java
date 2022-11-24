package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

//show logout success message and button to login page
public class logout {

    @FXML
    private Button logout;

    public void goToLogin(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Sample.fxml");
    }
}
