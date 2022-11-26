package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sql.DBAccess;

//reset password
public class modifyAccount {
	public modifyAccount() {

    }

    @FXML
    private Button modify;
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
	
    public void modifyCompleted(ActionEvent event) throws IOException {
        checkPassword();
    }
    //check if textfield is filled, password and reentered password is matching 
    private void checkPassword() throws IOException {
        Main m = new Main();
        if(password.getText().isEmpty() && securityQuestion.getText().isEmpty() && username.getText().isEmpty())
        	invalidInput.setText("Please enter your data.");
        else if(!password.getText().toString().equals(repassword.getText().toString()))
        	invalidInput.setText("Passwords don't match! Try again");
        else { // insert into database if proper fields are populated
        	boolean result = DBAccess.resetPassword(username.getText(), securityQuestion.getText(), password.getText());
        	if(result)
        		m.changeScene("success.fxml");
        	else
        		invalidInput.setText("Invalid Username or Security Answer");
        }
    }
}
