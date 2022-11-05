package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;


import javafx.fxml.FXML;
import objects.Course;
public class CourseBrowser {
	@FXML
    private Button create;
	@FXML
    private Button view;
	@FXML
	private Button delete;
	@FXML
	private Button rename;
	// add logout
	@FXML
	private Button logout;
	@FXML
	ComboBox<String> dropdownCourse= new ComboBox<>();
	
	
	// must call it upon creating to popular dropdown
	public void Creation() {
		ArrayList<String> options= Main.getCurrAcc().CourseListName;
		dropdownCourse.setItems((ObservableList<String>) options);
	}
	
	public void goToChosenCourse(ActionEvent event) throws IOException {
		 Main m = new Main();
	     m.changeScene("Sample.fxml");
	     
	}
	public void goToCreateScreen(ActionEvent event) throws IOException {
		for(int i=0; i< Main.getCurrAcc().CourseList.size(); i++) {
			if(Main.getCurrAcc().CourseList.get(i).Name== dropdownCourse.getValue()) {
				Main.setCurrentCourse( Main.getCurrAcc().CourseList.get(i));
			}
		}
		 Main m = new Main();
	     m.changeScene("CreateCourse.fxml");
	}
	public void goToRenameScreen (ActionEvent event) throws IOException {
		for(int i=0; i< Main.getCurrAcc().CourseList.size(); i++) {
			if(Main.getCurrAcc().CourseList.get(i).Name== dropdownCourse.getValue()) {
				Main.setCurrentCourse( Main.getCurrAcc().CourseList.get(i));
			}
		}
		Main m = new Main();
	    m.changeScene("RenameCourse.fxml");
	}
	public void RefreshAfterDeletion(ActionEvent event) throws IOException {
		 Main m = new Main();
	     m.changeScene("CourseBrowser.fxml");
	}
	//add logout
	public void logout(ActionEvent event) throws IOException {
		 Main m = new Main();
	     m.changeScene("logout.fxml");
	}
	
	 
}
