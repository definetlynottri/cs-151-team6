package application;

import objects.Card;
import objects.Course;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

//create course 
public class CreateScreen {
	@FXML
	private TextField name;
	@FXML
	private Button create;
	@FXML
	private Label invalidInput;
	//create course and add it to DataBase
	public void CreateCourse(ActionEvent event) throws IOException{
		if(name.getText().isEmpty()) 
        	invalidInput.setText("Please enter your data.");
        else {
        	Main.getCurrAcc().addCourse(name.getText());
        	Main m = new Main();
        	m.changeScene("courseBrowser.fxml");
        }
	}
}
