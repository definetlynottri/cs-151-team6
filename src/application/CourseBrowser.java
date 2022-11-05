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
	
	
	// must call it upon creating and refreshing to populate dropdown
	public void Creation() {
		ArrayList<String> options= Main.getCurrAcc().CourseListName;
		dropdownCourse.setItems((ObservableList<String>) options);
	}
	
	// goes to card screen when pressing view button with selected item, not implemented in 0.5
	public void goToChosenCourse(ActionEvent event) throws IOException {
		 Main m = new Main();
	     m.changeScene("Sample.fxml");
	     
	}
	
	// goes to create screen with selected item, uses static course in main to pass to next page
	public void goToCreateScreen(ActionEvent event) throws IOException {
		for(int i=0; i< Main.getCurrAcc().CourseList.size(); i++) {
			if(Main.getCurrAcc().CourseList.get(i).Name== dropdownCourse.getValue()) {
				Main.setCurrentCourse( Main.getCurrAcc().CourseList.get(i));
			}
		}
		 Main m = new Main();
	     m.changeScene("CreateCourse.fxml");
	}
	
	//goes to rename screen with selected item, uses static course in main to pass to next page
	public void goToRenameScreen (ActionEvent event) throws IOException {
		for(int i=0; i< Main.getCurrAcc().CourseList.size(); i++) {
			if(Main.getCurrAcc().CourseList.get(i).Name== dropdownCourse.getValue()) {
				Main.setCurrentCourse( Main.getCurrAcc().CourseList.get(i));
			}
		}
		Main m = new Main();
	    m.changeScene("RenameCourse.fxml");
	}
	
	// deletes the item then refreshes 
	public void RefreshAfterDeletion(ActionEvent event) throws IOException {
		for(int i=0; i< Main.getCurrAcc().CourseList.size(); i++) {
			if(Main.getCurrAcc().CourseList.get(i).Name== dropdownCourse.getValue()) {
				Main.getCurrAcc().CourseListName.remove(Main.getCurrAcc().CourseList.get(i).Name);
				Main.getCurrAcc().CourseList.remove(Main.getCurrAcc().CourseList.get(i));
			}
		}
		Creation();
		Main m = new Main();
	    m.changeScene("CourseBrowser.fxml");
	}
	//add logout
	public void logout(ActionEvent event) throws IOException {
		 Main m = new Main();
	     m.changeScene("logout.fxml");
	}
	
	 
}
