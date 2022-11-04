
public class Card {
	String UpperText;
	String LowerText;
	boolean learned= false;
	int SQLID;
	
	public Card(String UT, String LT) {
		this.UpperText= UT;
		this.LowerText= LT;
	}
	
	public Boolean UpdateCard(String UT, String LT) {
		this.UpperText= UT;
		this.LowerText= LT;
		return true;
	}
	public Boolean UpdateLearned() {
		this.learned= true;
		return true;
	}
}
