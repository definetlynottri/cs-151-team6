package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sql.DBAccess;
//create account
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
	
    //check if textfield is filled, password and re entered password is filled, and if it's okay, put it into DataBase
    private void checkAccount() throws IOException {
        Main m = new Main();
        
        if(username.getText().isEmpty() && password.getText().isEmpty() && securityQuestion.getText().isEmpty()) // checks that all fields are entered
        	invalidInput.setText("Please enter your data.");
        else if(!password.getText().toString().equals(repassword.getText().toString())) // mismatch passwords
        	invalidInput.setText("Password not match! Type again");
        else { // adds the account to the database
        	String uName = username.getText();
        	String pass = password.getText();
        	String sQuest = securityQuestion.getText();
        	Boolean sqlSuccess = DBAccess.insertAccount(uName, pass, sQuest); // true if inserted into db, false otherwise
        	if(sqlSuccess) 
        		m.changeScene("success.fxml");
        	else
        		invalidInput.setText("SQL INSERT failure."); // shouldn't be able to see this, is a GUI locking the database?
        }
    }
}
