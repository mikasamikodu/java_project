package biz.impl;

import java.util.List;

import biz.Recordbiz;
import dao.RecordDao;
import dao.impl.RecordDaoImpl;
import emptity.Record2;

public class RecordbizImpl implements Recordbiz {
	private RecordDao recordDao=null;
	
	public RecordbizImpl() {
		recordDao=new RecordDaoImpl();
	}
	@Override
	public List<Record2> selectUserRecord(String uname) {
		return recordDao.selectRecordUname(uname);
	}

	@Override
	public List<Record2> selectDVDRecord(String dname) {
		return recordDao.selectRecordDname(dname);
	}

	@Override
	public List<Record2> selectReturnRecord(String uname) {
		return recordDao.selectRecordTime(true, uname);
	}

	@Override
	public List<Record2> selectLendRecord(String uname) {
		return recordDao.selectRecordTime(false, uname);
	}

	@Override
	public List<Record2> selectAllRecord() {
		return recordDao.selectRecord();
	}

}
