package application;

import objects.Course;
import java.io.IOException;
//import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RenameCourse {
	@FXML
	private TextField currentName;
	@FXML
	private TextField changingName;
	@FXML
	private Button rename;
	
	public void CreateCourse(ActionEvent event) throws IOException {
		//comment out the error
		//String name= Main.getCurrentCourse().getName();
		
		String oldName = currentName.getText();
		String newName = changingName.getText();
		Course tCourse = null;
		for(Course course:Main.getCurrAcc().CourseList) {
			if(course.Name.equals(oldName))
				tCourse = course;
		}
		if(tCourse !=null) {
			tCourse.rename(newName);
		}
		
		
		Main m = new Main();
	    m.changeScene("courseBrowser.fxml");
	}
}
