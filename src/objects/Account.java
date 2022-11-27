/**
 * Manages the account and card after login
 */
package objects;
import java.util.ArrayList;

import application.Main;
import sql.DBAccess;

// Holds an account in memory
public class Account {
	String Password;
	private String Username;
	String SecQuestion;
	private String SecAnswer;
	public ArrayList<Course> CourseList;
	public ArrayList<String> CourseListName;
	private int accountID;
	
	// Empty account, only use for inserting account
	public Account(String name) {
		this.Username=name;
		this.CourseList = new ArrayList<Course>();
	}
	
	// Full data constructor
	public Account(int aID, String uName, String pass, String sAnswer, ArrayList<Course> cList) {
		Password = pass;
		Username = uName;
		SecAnswer = sAnswer;
		CourseList = cList;
		accountID = aID;
		
	}
	
	/*
	// Gets a list of names of courses
	public ArrayList<String> getCourseNames(){
		ArrayList<String> names = new ArrayList<String>();
		for(Course course:CourseList) {
			names.add(course.getName());
		}
		return names;
	}
	*/
	
	/**
	 * Gets the names of the courses
	 * @return
	 */
	public String[] getCourseNames() {
		String[] reNames = new String[this.CourseList.size()];
		for(int i=0; i<this.CourseList.size(); i++) {
			reNames[i] = CourseList.get(i).getName();
		}
		return reNames;
	}
	
	
	
	// Adds a course object to the account memory and file database
	public boolean addCourse(Course newCourse) {
		if(CourseList.contains(newCourse)) {
			return false;
		}
		CourseList.add(newCourse);
		CourseListName.add(newCourse.Name);
		return true;
	}
	
	/**
	 * Finds a course in log(n) time
	 * @param name the name of the course
	 * @return the course object found
	 */
	public Course findCourse(String name) {
		Course tCourse = null;
		for(Course course:CourseList) {
			if(course.Name.equals(name))
				tCourse = course;
		}
		return tCourse;
	}
	
	/**
	 * Add a course purely on the name
	 * @param name the name of the new course
	 * @return
	 */
	public boolean addCourse(String name) {
		int coursePrimaryKey = DBAccess.insertCourse(name, accountID);
		//System.out.println("Added Course with ID:"+ coursePrimaryKey);
		if(coursePrimaryKey>=0) {
			//System.out.println("Trying to load course with ID:"+ coursePrimaryKey);
			Course newCourse = DBAccess.loadCourse(coursePrimaryKey);
			//System.out.println("loaded course with ID:" + coursePrimaryKey);
			CourseList.add(newCourse);
			
			//#### Throws ERRORS!
			//CourseListName.add(newCourse.Name);
			
			System.out.println(String.format("Added Course Named:%s", newCourse.Name));
			System.out.println(this);
			
			return true;
		}
		return false;
	}
	
	/**
	 * Removes a course based on a course object reference
	 * Preferred over deleteCourse as no O(n) search is required
	 */
	public boolean removeCourse(Course removedCourse) {
		if(!CourseList.contains(removedCourse)) {
			return false;
		}
		DBAccess.deleteCourse(removedCourse.courseID); // deletes from file
		CourseList.remove(removedCourse); // deletes from memory
		return true;
	}
	
	/**
	 * Deletes a course based on name, currently O(n) time
	 * @param name
	 */
	public void deleteCourse(String name) {
		Course delCourse = findCourse(name);
		
		// Threading purposes
		if(delCourse !=null) {
			DBAccess.deleteCourse(delCourse.courseID);
			CourseList.remove(delCourse);
			System.out.println(String.format("Deleted Course Named:%s", delCourse.Name));
			System.out.println(this);
		}
	}
	
	public boolean rename(String newName) {
		this.Username= newName;
		return true;
	}
	
	public boolean checkPassword(String Password) {
		if (this.Password== Password){
			return true;
		}
		return false;
	}
	public boolean checkAns(String Answer) {
		if (this.SecAnswer== Answer){
			return true;
		}
		return false;
	}
	
	
	/**
	 * Generates a printable string representing this account
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(String.format("Account(ID:%d - username:%s - Courses:\n", accountID, Username));
		for(Course course:CourseList) {
			str.append("->"+course);
		}
		return str.toString();
	}
	
	/**
	 * Gets a list of course names in a string for printing
	 * @return string with the course names, each on one line
	 */
	public String Courses() {
		StringBuilder str = new StringBuilder();
		str.append(String.format("Account(Username:%s - Courses:\n", Username));
		for(Course course:CourseList) {
			str.append(String.format("->Course:%s\n", course.Name));
		}
		return str.toString();
	}
	
	public int getID() {
		return this.accountID;
	}
}
