package dao.impl;

import java.util.ArrayList;
import java.util.List;

import dao.RecordDao;
import emptity.Record;
import emptity.Record2;

public class RecordDaoImpl extends BaseDao implements RecordDao {

	@Override
	public boolean saveRecord(Record record) {
		String  sql="insert into records (uid,did,lendtime,returntime)values(?,?,?,?)";
		List<Object> list=new ArrayList<>();
		list.add(record.getUid());
		list.add(record.getDid());
		list.add(record.getLendtime());
		list.add(record.getReturntime());
		return this.operUpdate(sql, list);
	}

	@Override
	public boolean updateRecord(Record record) {
		String  sql="update records set uid=?,did=?,lendtime=?,returntime=? where id=?";
		List<Object> list=new ArrayList<>();
		list.add(record.getUid());
		list.add(record.getDid());
		list.add(record.getLendtime());
		list.add(record.getReturntime());
		list.add(record.getId());
		return this.operUpdate(sql, list);
	}

	@Override
	public Record selectRecordid(int rId) {
		List<Record> list1=null;
		String  sql="select id,uid,did,lendtime,returntime from records where id=?";
		List<Object> list=new ArrayList<>();
		list.add(rId);
		try {
			list1=this.operQuery(sql, list,Record.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(list1.size()>0) {
			return list1.get(0);
		}
		return null;
	}

	@Override
	public List<Record2> selectRecord() {
		List<Record2> list=null;
		String sql="select r.id,d.id,u.uname,d.dname,r.lendtime,r.returntime from users u,dvds d,records r where u.id=r.uid and d.id=r.did";
		try {
			list=this.operQuery(sql, null, Record2.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Record2> selectRecordUname(String uname) {
		List<Record2> list=null;
		String sql="select r.id,d.id as did,u.uname,d.dname,r.lendtime,r.returntime from  users u,dvds d,records r where u.id=r.uid and d.id=r.did and uname=?";
		List<Object> list1=new ArrayList<>();
		list1.add(uname);
		try {
			list=this.operQuery(sql, list1, Record2.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Record2> selectRecordDname(String dname) {
		List<Record2> list=null;
		String sql="select r.id,d.id as did,u.uname,d.dname,r.lendtime,r.returntime from  users u,dvds d,records r where u.id=r.uid and d.id=r.did and dname=?";
		List<Object> list1=new ArrayList<>();
		list1.add(dname);
		try {
			list=this.operQuery(sql, list1, Record2.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Record2> selectRecordTime(boolean flag, String uname) {
		List<Record2> list=null;
		String sql=null;
		if(flag) {
			sql="select r.id,d.id as did,u.uname,d.dname,r.lendtime,r.returntime from  users u,dvds d,records r where u.id=r.uid and d.id=r.did and returntime is not null and uname=?";
		}else {
			sql="select r.id,d.id as did,u.uname,d.dname,r.lendtime,r.returntime from  users u,dvds d,records r where u.id=r.uid and d.id=r.did and returntime is null and uname=?";
		}
		List<Object> list1=new ArrayList<>();
		list1.add(uname);
		try {
			list=this.operQuery(sql, list1, Record2.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
