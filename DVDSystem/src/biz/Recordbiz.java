package biz;

import java.util.List;

import emptity.Record2;

public interface Recordbiz {
	public List<Record2> selectUserRecord(String uname);
	public List<Record2> selectDVDRecord(String dname);
	public List<Record2> selectReturnRecord(String uname);
	public List<Record2> selectLendRecord(String uname);
	public List<Record2> selectAllRecord();
}
