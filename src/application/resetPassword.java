package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sql.DBAccess;

public class resetPassword {
	public resetPassword() {

    }

    @FXML
    private Button changePassword;
    @FXML
    private Label invalidInput;
    @FXML
    private TextField username;
    @FXML
    private TextField securityQuestion;
    @FXML
    private PasswordField changingPassword;
    @FXML
    private PasswordField repassword;

    public void userPassword(ActionEvent event) throws IOException {
        checkPassword();
    }

    private void checkPassword() throws IOException {
        Main m = new Main();
        
        if(changingPassword.getText().isEmpty() && securityQuestion.getText().isEmpty() && username.getText().isEmpty())
        	invalidInput.setText("Please enter your data.");
        else if(!changingPassword.getText().toString().equals(repassword.getText().toString()))
        	invalidInput.setText("Passwords don't match! Try again");
        else {
        	boolean result = DBAccess.resetPassword(username.getText(), securityQuestion.getText(), changingPassword.getText());
        	if(result)
        		m.changeScene("success.fxml");
        	else
        		invalidInput.setText("Invalid Username or Security Answer");
        }
        	
        /*	
        if(!securityQuestion.getText().toString().equalsIgnoreCase("sanjose")) {
        	invalidInput.setText("Security question is incorrect! Type again");
        } else if(!username.getText().toString().equals("javafx")) {
        	invalidInput.setText("Invalid username. Type again");
        } else if(!changingPassword.getText().toString().equals(repassword.getText().toString())) {
        	invalidInput.setText("Changing Password not match! Type again");
        } else if(changingPassword.getText().isEmpty() && securityQuestion.getText().isEmpty()) {
        	invalidInput.setText("Please enter your data.");
        } else {
        	m.changeScene("success.fxml");;
        }
        */
    }
}
