package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class success {

    @FXML
    private Button loginPage;

    public void gotoLogin(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("sample.fxml");
    }
}
