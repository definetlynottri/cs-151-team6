package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class createAccount {
	public createAccount() {

    }

    @FXML
    private Button create;
    @FXML
    private Label invalidInput;
    @FXML
    private TextField username;
    @FXML
    private TextField securityQuestion;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField repassword;

    public void userAccount(ActionEvent event) throws IOException {
        checkAccount();
    }

    private void checkAccount() throws IOException {
        Main m = new Main();
        if(username.getText().toString().equals("javafx")) {
        	invalidInput.setText("Existing username! Type again");
        } else if(!password.getText().toString().equals(repassword.getText().toString())) {
        	invalidInput.setText("Password not match! Type again");
        } else if(username.getText().isEmpty() && password.getText().isEmpty() && securityQuestion.getText().isEmpty()) {
        	invalidInput.setText("Please enter your data.");
        } else {
        	m.changeScene("success.fxml");;
        }
    }
}
