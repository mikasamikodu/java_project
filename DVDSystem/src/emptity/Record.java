package emptity;

public class Record {
	private int id;
	private int uid;
	private int did;
	private String lendtime;
	private String returntime;
	
	public Record() {
		
	}

	public Record(int id, int uid, int did, String lendtime, String returntime) {
		super();
		this.id = id;
		this.uid = uid;
		this.did = did;
		this.lendtime = lendtime;
		this.returntime = returntime;
	}

	public Record(int uid, int did, String lendtime, String returntime) {
		super();
		this.uid = uid;
		this.did = did;
		this.lendtime = lendtime;
		this.returntime = returntime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
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
