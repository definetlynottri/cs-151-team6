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
	private TextField currentName;
	@FXML
	private TextField changingName;
	@FXML
	private Button rename;
	@FXML
	private Label invalidInput;
	
	//get name of current name and new name
	//change names after system find it
	public void CreateCourse(ActionEvent event) throws IOException {
		String[] course = Main.getCurrAcc().getCourseNames();

		if(currentName.getText().isEmpty() || changingName.getText().isEmpty()) {
			invalidInput.setText("Please enter your data.");
		}
		for (int i = 0; i < course.length; i++) {
			if (course[i].equals(currentName.getText().toString())) {
				String oldName = currentName.getText();
				String newName = changingName.getText();
				Main.getCurrAcc().findCourse(oldName).rename(newName);
				System.out.println(Main.getCurrAcc()); // prints out the current state of the account
			
				Main m = new Main();
				m.changeScene("courseBrowser.fxml");
			} 
			if(i == course.length-1) {
				invalidInput.setText("Invalid course name");
			}
		} 
	}
}
