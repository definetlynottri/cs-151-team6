package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AfterLogin {

    @FXML
    private Button mainpage;

    public void gotoMainpage(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("CourseBrowser.fxml");
    }
}
