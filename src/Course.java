import java.util.ArrayList;

public class Course {
	String Name;
	ArrayList<Card> CardList;
	int SQLID;
	
	public Course(String name) {
		this.Name=name;
		this.CardList = new ArrayList<>();
	}
	public boolean addCard(Card newcard) {
		if(CardList.contains(newcard)) {
			return false;
		}
		CardList.add(newcard);
		return true;
	}
	
	public boolean removeCard(Card removedCard) {
		if(!CardList.contains(removedCard)) {
			return false;
		}
		CardList.remove(removedCard);
		return true;
	}
	
	public boolean rename(String newName) {
		this.Name= newName;
		return true;
	}
}
