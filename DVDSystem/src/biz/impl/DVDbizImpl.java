package biz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import biz.DVDbiz;
import dao.DVDDao;
import dao.RecordDao;
import dao.impl.DVDDaoImpl;
import dao.impl.RecordDaoImpl;
import emptity.DVD;
import emptity.Record;

public class DVDbizImpl implements DVDbiz {
	private DVDDao dvdDao=null;
	private RecordDao recordDao=null;
	
	public DVDbizImpl() {
		dvdDao=new DVDDaoImpl();
		recordDao=new RecordDaoImpl();
	}
	@Override
	public boolean saveDVD(DVD dvd) {
		return dvdDao.saveDVD(dvd);
	}

	@Override
	public boolean delDVD(int did) {
		return dvdDao.delDVD(did);
	}

	@Override
	public boolean modifyDVD(DVD dvd) {
		return dvdDao.updateDVD(dvd);
	}

	@Override
	public List<DVD> selectAllDVD() {
		return dvdDao.selectDVD();
	}

	@Override
	public List<DVD> select_top_five() {
		return dvdDao.selectDVDNumber(0, 5);
	}

	@Override
	public DVD selectDVDName(String dname) {
		return dvdDao.selectDVDName(dname);
	}

	@Override
	public DVD selectDVDId(int did) {
		return dvdDao.selectDVDId(did);
	}

	@Override
	public int lendDVD(int did, int uid) {
		DVD dvd=dvdDao.selectDVDId(did);
		if(dvd==null) {
			return 0;
		}else{
			if(dvd.getStatus()==0) {
				return 1;
			}else {
				dvd.setStatus(0);
				dvd.setDcount(dvd.getDcount()+1);
				boolean flag1=dvdDao.updateDVD(dvd);
				Record record=new Record(uid, dvd.getId(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()), null);
				boolean flag2=recordDao.saveRecord(record);
				if(flag1&&flag2) {
					return 2;
				}else {
					return 3;
				}
			}
		}
	}

	@Override
	public int returnDVD(int rid) {
		Record record=recordDao.selectRecordid(rid);
		if(record==null) {
			return 0;
		}else {
			if(record.getReturntime()!=null){
				return 1;
			}else {
				record.setReturntime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				boolean flag1=recordDao.updateRecord(record);
				DVD dvd=dvdDao.selectDVDId(record.getDid());
				dvd.setStatus(1);
				boolean flag2=dvdDao.updateDVD(dvd);
				if(flag1&&flag2) {
					return 2;
				}else {
					return 3;
				}
			}
		}
	}

	@Override
	public List<DVD> canLendDVD() {
		return dvdDao.selectDVDStatus(1);
	}

	@Override
	public List<DVD> hasLendDVD() {
		return dvdDao.selectDVDStatus(0);
	}

}
