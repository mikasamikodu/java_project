package string;

public class StringCompareEMP {

//	比较字符串
	public static void main(String[] args) {
		String str1 = "bsdhjfbd";
		String str2 = "sdhj";
		/*将字符串转化为字符数组并逐个比较数组里的字符，直到出现两者不一样时，返回两者的ASCll的差值
		 * 如果一个字符串的前面部分与另一个字符串相同，则返回两个字符串长度之差
		 */
		System.out.println(str1.compareTo(str2));
		/*首先创建一个CaseInsensitiveComparator类的实例，然后用它去比较这两个字符串，
		 * 使用CharAt循环比较两者字符串中的字符，但是需要先将两者转化为大写字符再比较，比较后如不同就将字符转化为小谢后再比较
		 * 如果还不同就返回两个字符的ASCll的差值。如果一个字符串的前面部分与另一个字符串相同，则返回两个字符串长度之差
		 */
		System.out.println(str2.compareToIgnoreCase(str1));
	}

}
