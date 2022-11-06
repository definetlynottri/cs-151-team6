package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.Account;
import sql.DBAccess;
import javafx.event.ActionEvent;
import java.io.IOException;

//help user to login
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
    //implement button that lead to create account page
    public void createAccount(ActionEvent event) throws IOException {
    	m.changeScene("createAccount.fxml");
    }
    //implement button that lead to reset password page
    public void resetPassword(ActionEvent event) throws IOException {
    	m.changeScene("resetPassword.fxml");
    }
    //check if textfield are filled, and match input to data from DataBase
    private void checkLogin() throws IOException {
        if(username.getText().isEmpty() || password.getText().isEmpty()) 
        	wrongLogIn.setText("Please enter your data.");
        else {
        	String uName = username.getText();
        	String pass = password.getText();
        	int result = DBAccess.getAccount(uName, pass);
            if(result != -1) {
            	Account curAccount = DBAccess.loadAccount(result);
            	System.out.println("Loaded Account:");
            	System.out.println(curAccount);
            	Main.setCurrAcc(curAccount);
                m.changeScene("afterLogin.fxml");
            } else 
            	wrongLogIn.setText("Wrong username or password!");
        }
    }
}
