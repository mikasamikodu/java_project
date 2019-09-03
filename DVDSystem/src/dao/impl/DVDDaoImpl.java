package dao.impl;

import java.util.ArrayList;
import java.util.List;

import dao.DVDDao;
import emptity.DVD;

public class DVDDaoImpl extends BaseDao implements DVDDao {

	@Override
	public boolean saveDVD(DVD dvd) {
		String  sql="insert into dvds (dname,dcount,status)values(?,?,?)";
		List<Object> list=new ArrayList<>();
		list.add(dvd.getDname());
		list.add(dvd.getDcount());
		list.add(dvd.getStatus());
		return this.operUpdate(sql, list);
	}

	@Override
	public boolean delDVD(int id) {
		String  sql="delete from dvds  where id=?";
		List<Object> list=new ArrayList<>();
		list.add(id);
		return this.operUpdate(sql, list);
	}

	@Override
	public boolean updateDVD(DVD dvd) {
		String  sql="update dvds set dname=?,dcount=?,status=? where id=?";
		List<Object> list=new ArrayList<>();
		list.add(dvd.getDname());
		list.add(dvd.getDcount());
		list.add(dvd.getStatus());
		list.add(dvd.getId());
		return this.operUpdate(sql, list);
	}

	@Override
	public List<DVD> selectDVD() {
		String  sql="select id,dname,dcount,status from dvds";
		List<DVD> list=null;
		try {
			list=this.operQuery(sql, null, DVD.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public DVD selectDVDName(String name) {
		List<DVD> list1=null;
		String  sql="select id,dname,dcount,status from dvds where dname=?";
		List<Object> list=new ArrayList<>();
		list.add(name);
		try {
			list1=this.operQuery(sql, list, DVD.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(list1.size()>0) {
			return list1.get(0);
		}
		return null;
	}

	@Override
	public DVD selectDVDId(int id) {
		List<DVD> list1=null;
		String  sql="select id,dname,dcount,status from dvds where id=?";
		List<Object> list=new ArrayList<>();
		list.add(id);
		try {
			list1=this.operQuery(sql, list, DVD.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(list1.size()>0) {
			return list1.get(0);
		}
		return null;
	}

	@Override
	public List<DVD> selectDVDNumber(int index, int number) {
		String sql="select id,dname,dcount,status from dvds order by dcount desc limit "+index+" ,  "+number;
		List<DVD> list=null;
		try {
			list=this.operQuery(sql, null, DVD.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<DVD> selectDVDStatus(int status) {
		List<DVD> list1=null;
		String  sql="select id,dname,dcount,status from dvds where status=?";
		List<Object> list=null;
		list=new ArrayList<>();
		list.add(status);
		try {
			list1=this.operQuery(sql, list, DVD.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list1;	
	}

}
