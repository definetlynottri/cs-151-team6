package objects;
import java.util.ArrayList;

import sql.DBAccess;

public class Account {
	String Password;
	private String Username;
	String SecQuestion;
	private String SecAnswer;
	public ArrayList<Course> CourseList;
	public ArrayList<String> CourseListName;
	int accountID;
	
	public Account(String name) {
		this.Username=name;
		this.CourseList = new ArrayList<Course>();
	}
	
	public Account(int aID, String uName, String pass, String sAnswer, ArrayList<Course> cList) {
		Password = pass;
		Username = uName;
		SecAnswer = sAnswer;
		CourseList = cList;
		accountID = aID;
		
	}
	
	public ArrayList<String> getCourseNames(){
		ArrayList<String> names = new ArrayList<String>();
		for(Course course:CourseList) {
			names.add(course.getName());
		}
		return names;
	}
	
	public boolean addCourse(Course newCourse) {
		if(CourseList.contains(newCourse)) {
			return false;
		}
		CourseList.add(newCourse);
		CourseListName.add(newCourse.Name);
		return true;
	}
	
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
			
			
			return true;
		}
		return false;
	}
	
	public boolean removeCourse(Course removedCard) {
		if(!CourseList.contains(removedCard)) {
			return false;
		}
		CourseList.remove(removedCard);
		CourseListName.remove(removedCard.Name);
		return true;
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
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(String.format("Account(ID:%d - username:%s - Courses:\n", accountID, Username));
		for(Course course:CourseList) {
			str.append(course);
		}
		return str.toString();
	}
	
}
