package application;

import objects.Course;
import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
//change name of courses
public class RenameCourse {
	@FXML
	private TextField changingName;
	@FXML
	private Button rename;
	@FXML
	private Label invalidInput;
	
	//get name of current name and new name
	//change names after system find it
	public void CreateCourse(ActionEvent event) throws IOException {
		if(changingName.getText().isEmpty()) 
        	invalidInput.setText("Please enter your data.");
        else {
        	Main.getCurrentCourse().rename(changingName.getText()); // change the name
        	// return to course browser
        	Main m = new Main();
        	m.changeScene("courseBrowser.fxml");
		}
	}
}
