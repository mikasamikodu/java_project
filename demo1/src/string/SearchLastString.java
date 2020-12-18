package string;

public class SearchLastString {

	public static void main(String[] args) {
		String str1 = "dsbkjdsnk";
		String str2 = "bkj";
		int index = str1.lastIndexOf(str2);
		if(index != -1) 
			System.out.println(str2 + "在" + str1 + "中最后出现的位置是第" + index + "个字符之后");
		else
			System.out.println(str2 + "没有在" + str1 + "中出现");
	}

}
