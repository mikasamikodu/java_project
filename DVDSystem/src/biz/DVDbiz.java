package biz;

import java.util.List;

import emptity.DVD;

public interface DVDbiz {
	public boolean saveDVD(DVD dvd);
	public boolean delDVD(int did);
	public boolean modifyDVD(DVD dvd);
	public List<DVD> selectAllDVD();
	public List<DVD> select_top_five();
	public DVD selectDVDName(String dname);
	public DVD selectDVDId(int did);
	public int lendDVD(int did,int uid);
	public int returnDVD(int rid);
	public List<DVD> canLendDVD();
	public List<DVD> hasLendDVD();
}
