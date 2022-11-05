package objects;
import java.util.ArrayList;
import sql.DBAccess;

public class Course {
	public String Name;
	ArrayList<Card> CardList;
	int courseID;
	
	public Course(String name) {
		this.Name=name;
		this.CardList = new ArrayList<Card>();
	}
	
	/**
	 * Loads a course from the database
	 * @param courseID the database course ID
	 */
	public Course(int courseID) {
		Course tCourse = DBAccess.loadCourse(courseID); // temp course holder to fudge returning 2 values from db
		this.courseID = tCourse.courseID;
		this.Name = tCourse.Name;
		this.CardList = tCourse.CardList; // loads cards from the database
	}
	
	
	public Course(int cID, String inName, ArrayList<Card> cList) {
		this.courseID = cID;
		this.Name = inName;
		this.CardList = cList;
	}
	
	public boolean addCard(Card newCard) {
		if(CardList.contains(newCard)) {
			return false;
		}
		CardList.add(newCard);
		int primaryKey = DBAccess.insertCard(courseID, newCard.getLearned(), newCard.getUpperText(), newCard.getLowerText());
		newCard.setCardID(primaryKey); // updates the primary key for the card
		return true;
	}
	
	public boolean removeCard(Card removedCard) {
		if(!CardList.contains(removedCard)) {
			return false;
		}
		CardList.remove(removedCard);
		DBAccess.deleteCard(removedCard.cardID);
		return true;
	}
	
	public boolean rename(String newName) {
		System.out.println(String.format("Renamed Course from %s to %s", this.Name, newName));
		this.Name= newName;
		DBAccess.modifyCourse(this.courseID, newName); // updates the name of the course in the db
		return true;
	}
	
	public String getName() {return this.Name;}
	
	/**
	 * Creates a printable string of a course for logging
	 */
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append(String.format("Course(courseID:%d - Name:%s - Cards:\n", this.courseID, this.Name));
		for(Card card:this.CardList) {
			output.append("\t" + card.toString() + "\n");
		}
		return output.toString();
	}
	
	public boolean equals(String inName) {
		if(this.Name == inName)
			return true;
		else
			return false;
	}
	
}
