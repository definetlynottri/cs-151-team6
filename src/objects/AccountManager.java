package objects;
import java.util.ArrayList;

public class AccountManager {
	ArrayList<Account> Accounts= new ArrayList<>();
	
	public boolean addCourse(Account newAcc){
		if(Accounts.contains(newAcc)) {
			return false;
		}
		Accounts.add(newAcc);
		return true;
	}
	
	public boolean removeCourse(Account removedAcc) {
		if(!Accounts.contains(removedAcc)) {
			return false;
		}
		Accounts.remove(removedAcc);
		return true;
	}
}
