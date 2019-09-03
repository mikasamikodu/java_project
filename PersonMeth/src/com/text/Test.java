package com.text;

//import java.sql.Connection;
import java.util.Date;
//import java.util.UUID;

//import com.util.BaseDao;

public class Test {
	public void testDemo(){
		System.out.println("-----定时任务----"+new Date());
		 
	}
	/*public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.insert(0, "aaa");
		sb.insert(0,"bbb");
		System.out.println(sb.toString());
		

	}
  */
	    public static void main(String[] args) {  
	    	String flag_data_id="20001673202990030005";
	    	//long l = Long.parseLong(str); 

	    	//Long l1  = Long.parseLong(str.trim());

	    	//Long l2= Long.valueOf(str).longValue(); 
	    	System.out.println(flag_data_id.length());
	    	String flag2_data_id = flag_data_id.substring(4,flag_data_id.length()).trim();
	    	System.out.println(flag2_data_id);
	    } 
	
}
