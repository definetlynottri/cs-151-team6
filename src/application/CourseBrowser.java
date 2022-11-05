package application;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.contro.ComboBox;


import javafx.fxml.FXML;
import objects.Course;
public class CourseBrowser {
	@FXML
    private Button create;
	@FXML
    private Button view;
	@FXML
	private Button delete;
	ArrayList<String> options= Main.getCurrAcc().CourseListName;
	@FXMl
	private Combobox dropdownCourse= new ComboBox(options);
	
	public void goToChosenCourse(ActionEvent event) throws IOException {
		 Main m = new Main();
	     m.changeScene("Sample.fxml");
	     
	}
	public void goToCreateScreen(ActionEvent event) throws IOException {
		 Main.setCurrentCourse(dropdownCourse.getSelectedItem());
		 Main m = new Main();
	     m.changeScene("CreateCourse.fxml");
	}
	public void RefreshAfterDeletion(ActionEvent event) throws IOException {
		 Main m = new Main();
	     m.changeScene("CourseBrowser.fxml");
	}
	
	
	 
}
