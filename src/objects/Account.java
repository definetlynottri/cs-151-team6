package objects;
import java.util.ArrayList;

public class Account {
	String Password;
	private String Username;
	String SecQuestion;
	private String SecAnswer;
	ArrayList<Course> CourseList;
	int accountID;
	
	public Account(String name) {
		this.Username=name;
		this.CourseList = new ArrayList<>();
	}
	public boolean addCourse(Course newcard) {
		if(CourseList.contains(newcard)) {
			return false;
		}
		CourseList.add(newcard);
		return true;
	}
	
	public boolean removeCourse(Course removedCard) {
		if(!CourseList.contains(removedCard)) {
			return false;
		}
		CourseList.remove(removedCard);
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
	
}
