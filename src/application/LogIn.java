package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sql.DBAccess;
import javafx.event.ActionEvent;

import java.io.IOException;
public class LogIn {

    public LogIn() {

    }

    @FXML
    private Button button;
    @FXML
    private Button createAccount;
    @FXML
    private Button resetPassword;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    Main m = new Main();
    
    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();
    }
    public void createAccount(ActionEvent event) throws IOException {
    	m.changeScene("createAccount.fxml");
    }
    public void resetPassword(ActionEvent event) throws IOException {
    	m.changeScene("resetPassword.fxml");
    }

    private void checkLogin() throws IOException {
        if(username.getText().isEmpty() || password.getText().isEmpty()) 
        	wrongLogIn.setText("Please enter your data.");
        else {
        	String uName = username.getText();
        	String pass = password.getText();
        	int result = DBAccess.checkAccount(uName, pass);
            if(result != -1) {
            	System.out.println("Success! AccountID:" + result);
                m.changeScene("afterLogin.fxml");
            } else 
            	wrongLogIn.setText("Wrong username or password!");
        }
    	/*
        if(username.getText().toString().equals("javafx") && password.getText().toString().equals("151")) {
            wrongLogIn.setText("Success!");

            m.changeScene("afterLogin.fxml");
        } else if(username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogIn.setText("Please enter your data.");
        } else {
            wrongLogIn.setText("Wrong username or password!");
        }
        */
    }
}