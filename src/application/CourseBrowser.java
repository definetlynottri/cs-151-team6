package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;


import javafx.fxml.FXML;
import objects.Course;
import sql.DBAccess;
public class CourseBrowser {
	@FXML
	private ListView<String> coursesListView;
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
	private Button showCourse;
	@FXML
	private Label show;
	@FXML
	ComboBox<String> dropdownCourse= new ComboBox<>();
	@FXML
	private Button deleteAccount;
	@FXML
	private Button modifyAccount;

	ArrayList<String> courseNames;
	ArrayList<Course> courseIndexes;
	
	// must call it upon creating and refreshing to populate dropdown
	public void Creation() {
		ArrayList<String> options= new ArrayList<>(Arrays.asList(Main.getCurrAcc().getCourseNames()));
		//dropdownCourse.setItems((ObservableList<String>) options);
		dropdownCourse.setItems(FXCollections.observableArrayList(options));
	}
	
	// Displays courses on init
	@FXML
	public void initialize() {
		//show.setText(Main.getCurrAcc().Courses());
		this.courseNames = new ArrayList<String>();
		this.courseIndexes = new ArrayList<Course>();
		for(Course c:Main.getCurrAcc().CourseList) {
			courseNames.add(c.getName()); // add the name of the course
			courseIndexes.add(c); // add the course index
		}
		coursesListView.getItems().addAll(courseNames); // adds all the courses to 

	}
	
	/**
	 * Changes the current course based on selection in the list view
	 */
	private void changeCurrentCourse() {
		int selection = coursesListView.getSelectionModel().getSelectedIndex(); // gets the selected index
		Main.setCurrentCourse(courseIndexes.get(selection)); // changes the course to the selection
	}
	
	// goes to card screen when pressing view button with selected item, not implemented in 0.5
	public void goToChosenCourse(ActionEvent event) throws IOException {		
		Main m = new Main();
		changeCurrentCourse(); // changes the course to the selection
		m.changeScene("viewCards.fxml"); // pops the view cards screen
	}
	
	// goes to create screen with selected item, uses static course in main to pass to next page
	public void goToCreateScreen(ActionEvent event) throws IOException {
		for(int i=0; i< Main.getCurrAcc().CourseList.size(); i++) {
			if(Main.getCurrAcc().CourseList.get(i).Name== dropdownCourse.getValue()) {
				Main.setCurrentCourse( Main.getCurrAcc().CourseList.get(i));
			}
		}
		 Main m = new Main();
	     m.changeScene("createScreen.fxml");
	}
	
	//goes to rename screen with selected item, uses static course in main to pass to next page
	public void goToRenameScreen (ActionEvent event) throws IOException {
		/*
		for(int i=0; i< Main.getCurrAcc().CourseList.size(); i++) {
			if(Main.getCurrAcc().CourseList.get(i).Name== dropdownCourse.getValue()) {
				Main.setCurrentCourse( Main.getCurrAcc().CourseList.get(i));
			}
		}
		*/
		changeCurrentCourse(); // changes the course cursor to the selected one
		Main m = new Main();
	    m.changeScene("renameCourse.fxml");
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
	    m.changeScene("deleteCourse.fxml");
	}
	//add logout
	public void logout(ActionEvent event) throws IOException {
		 Main m = new Main();
	     m.changeScene("logout.fxml");
	}
	
	/**
	 * Deletes a course
	 * @param event
	 * @throws IOException
	 */
	public void deleteCourse(ActionEvent event) throws IOException {
		int selection = coursesListView.getSelectionModel().getSelectedIndex(); // gets the selected index
		if(selection >= 0) { // checks that something was selected
			Main m = new Main();
			Main.getCurrAcc().removeCourse(courseIndexes.get(selection));
			System.out.println(Main.getCurrAcc());
			m.changeScene("courseBrowser.fxml");
		}
	}
	
	public void deleteAccount(ActionEvent event) throws IOException {
		boolean success = DBAccess.deleteAccount(Main.getCurrAcc().getID());
		if(success) {
			System.out.println("Deleted Active Account");
			Main m = new Main();
		    m.changeScene("success.fxml");
		}
	}
	
	//modify Account 
	public void modifyAccount(ActionEvent event) throws IOException {
		 Main m = new Main();
	     m.changeScene("modifyAccount.fxml");
	}
}

