package com.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import com.dao.ImportCorpDao;
import com.entity.HeadManage;
import com.entity.Sa_Opperson_Oporg;
import com.entity.resphoneManage;
import com.util.BaseDao;
import com.util.ImportPersonInfo;
import com.util.ToXmlUtilOgnDept;

public class ImportCorpDaoImpl implements ImportCorpDao {
	Logger log = ImportPersonInfo.log;

	HeadManage hm = null;
	resphoneManage rpm = new resphoneManage();
	ToXmlUtilOgnDept tu = new ToXmlUtilOgnDept();
	Sa_Opperson_Oporg soo = null;
	BaseDao bd=new BaseDao();
	public String importCorpInfo(List<Object> obj) {
		hm = new HeadManage();
		hm = (HeadManage) obj.get(1);
		if (hm.getOperaterType() != null) {
			if (hm.getOperaterType().trim().equals("add")) {
				return add(obj);
			}
		}
		rpm.setDataId(hm.getDataId());
		rpm.setRtnCode("30");
		rpm.setRtnMsg("模型不存在");
		return tu.resphoneXml(rpm);
	}
	private String add(List<Object> obj) {
		soo = new Sa_Opperson_Oporg();
		soo = (Sa_Opperson_Oporg) obj.get(0);
		log.info("====开始接收组织机构信息");
		String sparent = "";
		if ("ogn".equals(soo.getSorgkindid())) {
			log.info("====机 构：" + soo.getCompanyname());
			sparent = soo.getSfid();
		} else if ("dpt".equals(soo.getSorgkindid())) {
			log.info("====所属机构：" + soo.getCompanyname());
			log.info("====部  门：" + soo.getDepartmentname());
			if (soo.getSuperiorDepartmentID() == null || soo.getSuperiorDepartmentID() == "") {
				sparent = soo.getCompanyid();
			} else {
				//String sorgkindID=soo.getSorgkindid();
				//soo.setSorgkindid("2");
				//int num1=findSoporg(soo);
				//if(num1==0){
				//	rpm.setDataId(hm.getDataId());
				//	rpm.setRtnCode("40");
				//	log.info("数据有误(未找到”"+soo.getDepartmentname()+"“上级部门“"+soo.getSuperiorDepartment()+"”)");
				//	rpm.setRtnMsg("数据有误(未找到”"+soo.getDepartmentname()+"“上级部门“"+soo.getSuperiorDepartment()+"”)");
				//	return tu.resphoneXml(rpm);
				//}
				//soo.setSorgkindid(sorgkindID);
				
				sparent = soo.getSuperiorDepartmentID();
			}
		}
		// 数据效验是否有相同部门导入入到本地
		soo.setSparent(sparent);
		int num = findSoporg(soo);
		if (num > 0) {
			if (soo.getSorgkindid().equals("dpt")) {
				rpm.setDataId(hm.getDataId());
				rpm.setRtnCode("40");
				log.info("数据有误('" + soo.getCompanyname() + "下" + soo.getDepartmentname() + "已经存在)");
				rpm.setRtnMsg("数据有误('" + soo.getCompanyname() + "下" + soo.getDepartmentname() + "已经存在)");
				return tu.resphoneXml(rpm);
			} else if(soo.getSorgkindid().equals("ogn")){
				rpm.setDataId(hm.getDataId());
				rpm.setRtnCode("40");
				log.info("数据有误('" + soo.getSfname() + "'下'" + soo.getCompanyname() + "已经存在)");
				rpm.setRtnMsg("数据有误('" + soo.getSfname() + "'下'" + soo.getCompanyname() + "已经存在)");
				return tu.resphoneXml(rpm);
			}
		}
		try {
			String insertSql = "insert into sa_opperson_saoporg(personMechID,sfname,sfid,companyid,company,departmentid,"
					+ "departmentname,sorgkindid,sparent,svalidstate,SUPERIORDEPARTMENT,SUPERIORDEPARTMENTID,CURRENTTIME,zsjState,arrt1,arrt2,arrt3,arrt4,arrt5,arrt6) "
					+ "values(seq_spersonMechID.Nextval,?,?,?,?,?,?,?,?,?,?,?,to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),0,?,?,?,?,?,?)";
			Object objInfo[] = { soo.getSfname(), soo.getSfid(), soo.getCompanyid(), soo.getCompanyname(),
					soo.getDepartmentid(), soo.getDepartmentname(), soo.getSorgkindid(), sparent,soo.getSvalidstate(),
					soo.getSuperiorDepartment(), soo.getSuperiorDepartmentID(),soo.getArrt1(),soo.getArrt2(),soo.getArrt3(),soo.getArrt4(),soo.getArrt5(),soo.getArrt6()};
			int i = bd.executeSQL(insertSql, objInfo);
			rpm.setDataId(hm.getDataId());
			rpm.setRtnCode("10");
			rpm.setRtnMsg("已经执行完成，接收成功	");
			if (i < 1) {
				System.out.println(i);
				rpm.setDataId(hm.getDataId());
				rpm.setRtnCode("40");
				rpm.setRtnMsg("数据有误(合法性错误)");
			} else {
				log.info("====从主数据接收成功");
			}

		} catch (Exception e) {
			log.info("====从主数据接收失败");
			log.error("exception", e);
			e.printStackTrace();
		}
		return tu.resphoneXml(rpm);
	}
	//效验部门
	public int findSoporg(Sa_Opperson_Oporg soo) {
		bd=new BaseDao();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		int result = 0;
		System.out.println(soo.getSparent());
		try {
			con=bd.getConnection();
			String findSaoporg = "";
			if (soo.getSorgkindid().equals("dpt")) {
				findSaoporg = "select * from sa_opperson_saoporg where departmentname='" + soo.getDepartmentname()
						+ "' and sorgkindid='" + soo.getSorgkindid() + "' and sparent='" + soo.getSparent() + "'";
			}
			if (soo.getSorgkindid().equals("ogn")){
				findSaoporg = "select * from sa_opperson_saoporg where company='" + soo.getCompanyname()
						+ "' and sorgkindid='" + soo.getSorgkindid() + "' and sparent='" + soo.getSparent() + "'";
			}
			if (soo.getSorgkindid().equals("2")){
				findSaoporg = "select * from sa_opperson_saoporg where departmentname='" + soo.getSuperiorDepartment()
				+ "' and departmentid='"+soo.getSuperiorDepartmentID()+"' and sorgkindid='dpt'";
			}
			System.out.println(findSaoporg);
			pstm=con.prepareStatement(findSaoporg);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result++;
			}
			System.out.println("result:" + result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}