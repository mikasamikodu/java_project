package emptity;

public class Record2 {
	private int id;
	private int did;
	private String uname;
	private String dname;
	private String lendtime;
	private String returntime;
	
	public Record2() {
		
	}
	public Record2(int id, String uname, String dname, String lendtime, String returntime) {
		super();
		this.id = id;
		this.uname = uname;
		this.dname = dname;
		this.lendtime = lendtime;
		this.returntime = returntime;
	}
	public Record2(String uname, String dname, String lendtime, String returntime) {
		super();
		this.uname = uname;
		this.dname = dname;
		this.lendtime = lendtime;
		this.returntime = returntime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLendtime() {
		return lendtime;
	}
	public void setLendtime(String lendtime) {
		this.lendtime = lendtime;
	}
	public String getReturntime() {
		return returntime;
	}
	public void setReturntime(String returntime) {
		this.returntime = returntime;
	}
	
}
