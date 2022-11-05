package objects;
import java.util.ArrayList;

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
	
	public boolean addCourse(Course newCourse) {
		if(CourseList.contains(newCourse)) {
			return false;
		}
		CourseList.add(newCourse);
		CourseListName.add(newCourse.Name);
		return true;
	}
	
	public boolean removeCourse(Course removedCard) {
		if(!CourseList.contains(removedCard)) {
			return false;
		}
		CourseList.remove(removedCard);
		CourseListName.remove(newCourse.Name);
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
