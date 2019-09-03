package emptity;

public class User {
	private int id;
	private String uname;
	private String upass;
	private int utype;

	public User() {

	}

	public User(int id, String uname, String upass, int utype) {
		super();
		this.id = id;
		this.uname = uname;
		this.upass = upass;
		this.utype = utype;
	}

	public User(String uname, String upass, int utype) {
		super();
		this.uname = uname;
		this.upass = upass;
		this.utype = utype;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpass() {
		return upass;
	}

	public void setUpass(String upass) {
		this.upass = upass;
	}

	public int getUtype() {
		return utype;
	}

	public void setUtype(int utype) {
		this.utype = utype;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", uname=" + uname + ", upass=" + upass + ", utype=" + utype + "]";
	}
	
}
