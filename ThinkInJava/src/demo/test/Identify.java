package demo.test;


public class Identify {

	
	public static String test(String id) {
		String[] ids = id.split("");
		if(ids.length!=18) {
			return "2";
		}
		String[] names = "7－9－10－5－8－4－2－1－6－3－7－9－10－5－8－4－2".split("－");
		int num = 0;
		for(int i=0;i<ids.length-1;i++) {
			num += Integer.parseInt(ids[i]) * Integer.parseInt(names[i]);
		}
		String[] number = "1－0－X－9－8－7－6－5－4－3－2".split("－");
		int num2 = num%11;
		String n1 = number[num2];
		int n2 = ids.length-1;
		String n3 = ids[n2];
		if(n1.equals(n3)) {
			return "0";
		}
		return "1";
	}
	public static void main(String[] args) {
		String[] nn = {"522229197803241611","410521197506029040","412728197708083880","000130702630403032","000130702680527062","000130702631225033","000130102760518211","000130702711007063","130703198303050912"};
		for(String n : nn) {
			String result = test(n);
			switch(result){
				case"0":
					System.out.println("正在测试的是"+n+",结果是身份证合规");
					break;
				case"1":
					System.out.println("正在测试的是"+n+",结果是身份证不合规");
					break;
				case"2":
					System.out.println("正在测试的是"+n+",结果是身份证长度不对");
					break;
				default:
					System.out.println("程序出错");
			}
		}
	}

}
