package com.importInfo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.entity.Sa_Opperson_Oporg;
import com.importInfo.ImportOgnDpt;
import com.util.BaseDao;
import com.util.DateResultNotNull;
import com.util.ImportPersonInfo;

public class ImportOgnDepImpl implements ImportOgnDpt {
	Logger log = ImportPersonInfo.log;
	BaseDao bd=null;
	public void importOgnDptInfo() {
		log.info("++++++++++++++");
		log.info("++++++++++++++");
		log.info("++++++++++++++");
		log.info("++++++++++++++");
		bd=new BaseDao();
		Sa_Opperson_Oporg soo = new Sa_Opperson_Oporg();
		DateResultNotNull drnn = new DateResultNotNull();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		try {
			con=bd.getConnection();
			// 查询从主数据接收组织机构信息为未导入swzc系统的状态进行导入
			// 同步公司
			String selectSql = "select * from sa_opperson_saOporg where zsjstate=0 and sorgkindid='ogn' and sfid<>'20000493'";
			pstm=con.prepareStatement(selectSql);		
			rs =pstm.executeQuery();
			while (rs.next()) {
				soo.setSfname(rs.getString("sfname"));
				soo.setSfid(rs.getString("sfid"));
				soo.setCompanyid(rs.getString("companyid"));
				soo.setCompanyname(rs.getString("company"));
				soo.setSorgkindid(rs.getString("sorgkindid"));
				soo.setSparent(rs.getString("sparent"));
				soo.setSvalidstate(rs.getString("svalidstate"));
				String svalidationOgn=drnn.validationOgn(soo);
				if("false".equals(svalidationOgn)){
					updateDptOgnSorgkindID(soo,"-4");
					log.info("合法性错误（”" + soo.getCompanyname() + "“该公司未获得实物资产录入授权）");
					
					log.info("====同步组织机构信息失败");
					
				}else{
				log.info("开始主数据公司同步");
				log.info("sfname:" + soo.getSfname());
				log.info("comPanyName:" + soo.getCompanyname());
				log.info("svalidState:" + soo.getSvalidstate());
				insertOng(soo);
				}
			}
			// 同步部门
			String selectdpt = "select * from sa_opperson_saOporg where zsjstate=0 and sorgkindid='dpt' and companyid <> '20000493' order by superiordepartment desc";
			pstm=con.prepareStatement(selectdpt);
			rs = pstm.executeQuery();
			while (rs.next()) {
				soo = new Sa_Opperson_Oporg();
				soo.setSfname(rs.getString("sfname"));
				soo.setSfid(rs.getString("sfid"));
				soo.setCompanyid(rs.getString("companyid"));
				soo.setCompanyname(rs.getString("company"));
				soo.setDepartmentid(rs.getString("departmentid"));
				soo.setDepartmentname(rs.getString("departmentname"));
				soo.setSuperiorDepartment(rs.getString("superiorDepartment"));
				soo.setSuperiorDepartmentID(rs.getString("superiorDepartmentID"));
				soo.setSorgkindid(rs.getString("sorgkindid"));
				soo.setSparent(rs.getString("sparent"));
				soo.setSvalidstate(rs.getString("svalidstate"));
				log.info("开始执行部门同步");
				
				String svalidationOgn=drnn.validationOgn(soo);
				if("false".equals(svalidationOgn)){
					log.info("合法性错误（”" + soo.getCompanyname() + "“该公司未获得实物资产录入授权）");
					
					log.info("====同步组织机构信息失败");
					
				}else{
					insertDpt(soo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			bd.getConnectionClose(con, pstm, rs);
		}
	}

	/**
	 * 同步部门信息
	 * 
	 * @param soo
	 * @throws Exception
	 */
	public void insertDpt(Sa_Opperson_Oporg soo) {
		bd=new BaseDao();
		String str[] = new String[5];
		String sfname = "";
		String sfcode = "";
		String sfid = "";
		String sparent = "";
		String ssequence = "";
		try {
			// 添加公司一级部门
			if (soo.getSuperiorDepartment() == null || soo.getSuperiorDepartment().equals("")) {
				// 效验添加的部门是否已经存在
				String result = selectDpt1(soo);
				if (result == "Y") {
					log.info(soo.getCompanyname() + "下的" + soo.getDepartmentname() + "已经存在,无须重复添加");
					updateDptOgnSorgkindID(soo,"2");
					return;
				}
				if (sparent == "false") {
					log.info("未查询到" + soo.getDepartmentname() + "的上级部门" + soo.getSuperiorDepartment());
					updateDptOgnSorgkindID(soo,"3");
					return;
				}
				soo.setSfname(soo.getCompanyname());
				str = selecOgnCurr(soo);
				sfname = str[0] + "/" + soo.getDepartmentname();
				sfcode = str[2] + "/" + soo.getDepartmentid();
				sparent = str[3];
				if (sparent == "false") {
					log.info("未查询到" + soo.getDepartmentname() + "的上级公司" + soo.getCompanyname());
					updateDptOgnSorgkindID(soo,"4");
					return;
				}
				sfid = str[1] + "/" + soo.getDepartmentid() + ".dpt";
				ssequence = str[4];	
			}
			// 添加公司二级部门
			if (soo.getSuperiorDepartment() != null) {
				String result = selectDpt1(soo);
				if (result == "Y") {
					log.info(soo.getCompanyname() + "/" + soo.getSuperiorDepartment() + "/" + soo.getDepartmentname()
							+ "已经存在,无须重复添加");
					updateDptOgnSorgkindID(soo,"2");
					return;
				}
				str = selecOgnCurr(soo);
				sfname = str[0] + "/" + soo.getDepartmentname();
				sfcode = str[2] + "/" + soo.getDepartmentid();
				sparent = str[3];
				sfid = str[1] + "/" + soo.getDepartmentid() + ".dpt";
				if (sparent == "false") {
					log.info("未查询到" + soo.getDepartmentname() + "的上级部门" + soo.getSuperiorDepartment());
					updateDptOgnSorgkindID(soo,"");
					return;
				}
				ssequence=str[4];		
			}
			if (soo.getSvalidstate().equals("启用")) {
				soo.setSvalidstate("1");
			} else {
				soo.setSvalidstate("0");
			}
			log.info("正在同步" + soo.getCompanyname() + "下的" + soo.getDepartmentname());

			String insertOporg = "insert into sa_oporg(sid,sname,scode,sfname,sfcode,sfid,sorgkindid,svalidstate,sparent,sdescription,version,ssequence) "
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?)";
			Object[] obj = { soo.getDepartmentid(), soo.getDepartmentname(), soo.getDepartmentid(), sfname, sfcode,
					sfid, soo.getSorgkindid(), soo.getSvalidstate(), sparent, "ZSJ", "0",ssequence };
			int i = bd.executeSQL(insertOporg, obj);
			if (i > 0) {
				log.info(sfname + "同步成功");
				//修改状态
				updateDptOgnSorgkindID(soo,"1");
			} else {
				log.info(sfname + "同步失败");
				updateDptOgnSorgkindID(soo,"-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateDptOgnSorgkindID(Sa_Opperson_Oporg soo,String zsjState) throws Exception {
		bd=new BaseDao();
		try {
			String updateSql = "update sa_opperson_saOporg set zsjstate='"+zsjState+"',modifytime=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS')";
			if("dpt".equals(soo.getSorgkindid())){
				 updateSql+=" where departmentid='" + soo.getDepartmentid()+ "' and sorgkindid='dpt'";
			}else {
				updateSql+=" where companyid='"+soo.getCompanyid()+"' and sorgkindid='ogn'";
			}
			System.out.println(updateSql);
			int j = bd.executeSQL(updateSql, null);
			if (j < 1) {
				
				if("dpt".equals(soo.getSorgkindid())){
					log.info("”"+soo.getCompanyname() + "“下“"+soo.getDepartmentname()+"”修改同步状态[ZSJSTATE]失败");
				}else {
					log.info("”"+soo.getSfname() + "“下“"+soo.getCompanyname()+"”修改同步状态[ZSJSTATE]失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("修改同步状态异常",e);
		}
	}
	/***
	 * 同步公司
	 * @param soo
	 * @throws Exception
	 */
	public void insertOng(Sa_Opperson_Oporg soo) throws Exception {
		bd=new BaseDao();
		/*
		 * DateResultNotNull drnn=new DateResultNotNull(); String
		 * validation=drnn.validationOgn(soo); if("true"!=validation){ String
		 * sql="update sa_opperson_saOporg set zsjstate='9' where companyid='"
		 * +soo.getCompanyid()+"' and sorgkindid='ogn'"; int i =
		 * BaseDao.executeSQL(sql, null); if(i<1){
		 * log.info("修改sa_opperson_saOporg表中”"+soo.getCompanyname()+
		 * "“公司状态（ZSJSTATE=9）失败"); }else {
		 * log.info(soo.getCompanyname()+"未得到添加该公司的授权"); } return; }
		 */
		String str[] = new String[4];
		str = selecOgnCurr(soo);
		String sfname = str[0] + "/" + soo.getCompanyname();
		String sfcode = str[2] + "/" + soo.getCompanyid();
		String sparent = str[3];
		String sfid = str[1] + "/" + soo.getCompanyid() + ".ogn";
		// 如果返回时false字符串说明查询的公司不止一条数据
		if (sparent == "false") {
			log.info("组织机构添加失败,未查询到" + soo.getCompanyname() + "上级组织机构" + soo.getSfname() + "");
			updateDptOgnSorgkindID(soo,"4");
			return;
		}
		/*
		 * sfid += "/" + soo.getCompanyid() + ".ogn";
		 */
		if (soo.getSvalidstate().equals("启用")) {
			soo.setSvalidstate("1");
		} else {
			soo.setSvalidstate("0");
		}
		// 判断添加的公司是否存在
		String result = selectOgn(soo);
		if (result != null) {
			log.info(soo.getCompanyname() + "已存在，无须重复添加");
			updateDptOgnSorgkindID(soo,"2");
		} else {
			try {
				log.info("正在同步公司" + soo.getCompanyname());
				String insertOporg = "insert into sa_oporg(sid,sname,scode,sfname,sfcode,sfid,sorgkindid,svalidstate,sparent,sdescription,version) "
						+ " values(?,?,?,?,?,?,?,?,?,?,?)";
				Object[] obj = { soo.getCompanyid(), soo.getCompanyname(), soo.getCompanyid(), sfname, sfcode, sfid,
						soo.getSorgkindid(), soo.getSvalidstate(), sparent, "ZSJ", "0" };
				int i = bd.executeSQL(insertOporg, obj);
				if (i > 0) {
					log.info(soo.getCompanyname() + "同步成功");
					updateDptOgnSorgkindID(soo,"1");
				} else {
					log.info(soo.getCompanyname() + "同步失败");

				}

			} catch (Exception e) {
				log.info(soo.getCompanyname() + "同步失败");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 查询同步公司的上级公司/部门
	 * 
	 * @param soo
	 * @return
	 */
	public String[] selecOgnCurr(Sa_Opperson_Oporg soo) throws Exception {
		bd=new BaseDao();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		String[] str = new String[5];
		int i = 0;
		try {
			con=bd.getConnection();
			
			String selectOgn = "";
			if (soo.getSuperiorDepartment() == null) {
				selectOgn = "select * from sa_oporg where sname='" + soo.getSfname() + "' and sorgkindid='ogn'";
			} else {
				selectOgn = "select * from sa_oporg where sname='" + soo.getSuperiorDepartment()+ "' and sorgkindid='dpt' and sparent=(select sid from sa_oporg where sname='"+ soo.getCompanyname() + "')";
			}
			pstm=con.prepareStatement(selectOgn);
			rs = pstm.executeQuery();
			while (rs.next()) {
				str[0] = rs.getString("sfname");
				str[1] = rs.getString("sfid");
				str[2] = rs.getString("sfcode");
				str[3] = rs.getString("sid");
				str[4] = rs.getString("ssequence");

				i++;
			}
			if (i != 1) {
				str[3] = "false";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			bd.getConnectionClose(con, pstm, rs);
		}
		return str;
	}

	/**
	 * 查询添加公司是否存在
	 * 
	 * @param soo
	 * @return
	 */
	public String selectOgn(Sa_Opperson_Oporg soo) throws Exception {
		bd=new BaseDao();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		String result = null;
		try {
			con=bd.getConnection();
			String selectOgn = "select * from sa_oporg where sname='" + soo.getCompanyname() + "' and sorgkindid='ogn'";
			pstm=con.prepareStatement(selectOgn);
			rs =pstm.executeQuery();
			if (rs.next()) {
				result = "Y";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			bd.getConnectionClose(con, pstm, rs);
		}
		return result;
	}

	/**
	 * 验证同步的部门是否存在
	 * 
	 * @param soo
	 * @return
	 */
	public String selectDpt1(Sa_Opperson_Oporg soo) throws Exception {
		bd=new BaseDao();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		String result = "";
		String sql = "";
		try {
			con=bd.getConnection();
			if (soo.getSuperiorDepartment() == null) {
				sql = "select * from sa_oporg where sname='" + soo.getDepartmentname()
						+ "' and sparent=(select sid from sa_oporg where sname='" + soo.getCompanyname()
						+ "' and sorgkindid='ogn') and sorgkindid='dpt'";
			} else {
				sql = "select * from sa_oporg where sname='" + soo.getDepartmentname()
						+ "' and sparent = (select sid  from sa_oporg where sname='" + soo.getSuperiorDepartment()
						+ "' and sorgkindid='dpt' and sparent = (select sid from sa_oporg where sname='"
						+ soo.getCompanyname() + "') and sorgkindid='dpt')";
			}
			pstm=con.prepareStatement(sql);
			
			rs =pstm.executeQuery();
			if (rs.next()) {
				result = "Y";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			bd.getConnectionClose(con, pstm, rs);
		}
		return result;
	}
}
