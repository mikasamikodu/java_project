package dao;

import java.util.List;

import emptity.Record;
import emptity.Record2;

public interface RecordDao {
	public boolean saveRecord(Record record);
	public boolean updateRecord(Record record);
	public Record selectRecordid(int rId);
	public List<Record2> selectRecord();
	public List<Record2> selectRecordUname(String uname);
	public List<Record2> selectRecordDname(String dname);
	public List<Record2> selectRecordTime(boolean flag,String uname);
}
