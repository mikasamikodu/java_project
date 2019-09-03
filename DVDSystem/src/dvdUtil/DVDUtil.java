package dvdUtil;

public class DVDUtil {
	public static boolean isNumber(String dcount) {
		for(int i=0;i<dcount.length();i++) {
			if(!Character.isDigit(dcount.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
