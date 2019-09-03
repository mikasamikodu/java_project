package dao;

import java.util.List;

import emptity.DVD;

public interface DVDDao {
	public boolean saveDVD(DVD dvd);
	public boolean delDVD(int id);
	public boolean updateDVD(DVD dvd);
	public List<DVD> selectDVD();
	public DVD selectDVDName(String dname);
	public DVD selectDVDId(int id);
	public List<DVD> selectDVDNumber(int index,int number);
	public List<DVD> selectDVDStatus(int status);
}
