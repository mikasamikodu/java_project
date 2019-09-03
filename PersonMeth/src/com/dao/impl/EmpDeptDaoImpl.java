package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import com.dao.EmpDeptDao;
import com.entity.HeadManage;
import com.entity.Sa_Opperson_Oporg;
import com.entity.Saoporg;
import com.entity.Saopperson;
import com.entity.Vendor;
import com.entity.resphoneManage;
import com.util.BaseDao;
import com.util.DateResultNotNull;
import com.util.ImportPersonInfo;
import com.util.ToXmlUtil;

public class EmpDeptDaoImpl implements EmpDeptDao {
	protected Logger log = ImportPersonInfo.log;
	HeadManage hm = new HeadManage();
	resphoneManage rpm = new resphoneManage();
	ToXmlUtil tu = new ToXmlUtil();
	BaseDao bd = null;
	
	// @Override
	public String importName(List<Object> obj) {
		hm = (HeadManage) obj.get(2);
		if (hm.getOperaterType() != null) {
			if (hm.getOperaterType().trim().equals("add")) {
				log.info("====开始从主数据接收人员");
				return add(obj);
			}
		}
		rpm.setDataId(hm.getDataId());
		rpm.setRtnCode("30");
		rpm.setRtnMsg("模型不存在");
		return tu.resphoneXml(rpm);
	}

	/**
	 * @param obj
	 * @return
	 */
	public String add(List<Object> obj) {

		bd = new BaseDao();
		try {
			Saopperson emp = (Saopperson) obj.get(0);
			Saoporg dept = (Saoporg) obj.get(1);
			log.info("====开始从主数据接收人员的信息");
			log.info("====人员SID：" + emp.getSid());
			log.info("====姓 名：" + emp.getSname());
			log.info("====身份证：" + emp.getSidCard());
			log.info("====编码：" + emp.getSnameid());
			log.info("====公司：" + dept.getOrganization());
			log.info("====部门：" + dept.getDepartment());
			log.info("====是否兼职：" + emp.getArrt1());
			String sql1 = "";

			int j = findOpperson(emp);// 验证人员是否存在
			/*
			 * if (j != 0) {// 如果接口表sa_opperson_saoporg中人员信息存在则进行人员信息修改
			 * log.info("====该信息已存在，执行" + emp.getSname() + "的信息修改"); sql1 =
			 * "update sa_opperson_saOporg set sid=?,sname=?,snameid=?,sfname=?,sfcode=?,sfid=?,SORGKINDID=?,SSEQUENCE=?,SPARENT=?,SIDCARD=?,"
			 * + "SVALIDSTATE=?,SSEX=?,SBIRTHDAY=?,SJOINDATE=?,SMOBILEPHONE=?,"
			 * +
			 * "SOFFICEPHONE=?,DEPARTMENTID=?,DEPARTMENTNAME=?,COMPANYID=?,COMPANY=?,zsjstate='0',CURRENTTIME=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),"
			 * + "arrt1=?,arrt2=?,arrt3=?,arrt4=?,arrt5=?,arrt6=? where sid='" +
			 * emp.getSid() + "' and sidcard='" + emp.getSidCard() +
			 * "' and sorgkindid='psm'"; sql1=
			 * "update sa_opperson_saOporg set zsjstate='100' where sid='" +
			 * emp.getSid() + "' and sidcard='" + emp.getSidCard() +
			 * "' and sorgkindid='psm'"; } else
			 * {//如果接口表sa_opperson_saoporg中人员信息不存在则进行人员添加
			 * log.info("====开始从主数据执行" + emp.getSname() + "的信息添加"); sql1 =
			 * "insert into sa_opperson_saOporg(personMechID,sid,sname,snameid,sfname,sfcode,sfid,SORGKINDID,SSEQUENCE,SPARENT,SIDCARD,"
			 * + "SVALIDSTATE,SSEX,SBIRTHDAY,SJOINDATE,SMOBILEPHONE," +
			 * "SOFFICEPHONE,DEPARTMENTID,DEPARTMENTNAME,COMPANYID,COMPANY,zsjstate,CURRENTTIME,arrt1,arrt2,arrt3,arrt4,arrt5,arrt6)"
			 * +
			 * " values(seq_spersonMechID.Nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'0',to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),?,?,?,?,?,?)"
			 * ; }
			 */
			int i = 0;
			int zsjState = 0;
			if (j != 0) {// 如果接口表sa_opperson_saoporg中人员信息存在则进行人员信息修改

				if ("Y".equals(emp.getArrt1())) {
					log.info("====该信息已存在(兼职)，执行" + emp.getSname() + "上一条数据的信息修改");

					sql1 = "update sa_opperson_saOporg set zsjstate='100' where sid='" + emp.getSid()
							+ "' and sorgkindid='psm' and zsjstate='0' and arrt1<>'N'";
					i = bd.executeSQL(sql1, null);
				} else if ("N".equals(emp.getArrt1())) {
					log.info("====该信息已存在(兼职)，执行" + emp.getSname() + "上一条数据的信息修改");

					sql1 = "update sa_opperson_saOporg set zsjstate='100' where sid='" + emp.getSid()
							+ "' and sorgkindid='psm' and zsjstate='0' and arrt1<>'Y'";
					i = bd.executeSQL(sql1, null);
				}

			}
			// 进行人员添加
			log.info("====开始从主数据执行" + emp.getSname() + "的信息添加");
			sql1 = "insert into sa_opperson_saOporg(personMechID,sid,sname,snameid,sfname,sfcode,sfid,SORGKINDID,SSEQUENCE,SPARENT,SIDCARD,"
					+ "SVALIDSTATE,SSEX,SBIRTHDAY,SJOINDATE,SMOBILEPHONE,"
					+ "SOFFICEPHONE,DEPARTMENTID,DEPARTMENTNAME,COMPANYID,COMPANY,CURRENTTIME,ZSJSTATE,arrt1,arrt2,arrt3,arrt4,arrt5,arrt6)"
					+ " values(seq_spersonMechID.Nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),?,?,?,?,?,?,?)";

			String parentID = "";
			if (dept.getSorgKindId().equals("psm")) {
				parentID = dept.getDepartmentID();
			}
			Object[] deptResource1 = { emp.getSid(), emp.getSname(), emp.getSnameid(), dept.getOrganization(),
					dept.getSfcode(), dept.getOrganizationID(), dept.getSorgKindId(), emp.getSsequence(), parentID,
					emp.getSidCard(), emp.getSvalIdState(), emp.getSex(), emp.getBirthDay(), emp.getJoinDate(),
					emp.getSmobilePhone(), emp.getSofficePhone(), dept.getDepartmentID(), dept.getDepartment(),
					dept.getCompanyid(), dept.getCompany(), zsjState, emp.getArrt1(), emp.getArrt2(), emp.getArrt3(),
					emp.getArrt4(), emp.getArrt5(), emp.getArrt6() };

			i = bd.executeSQL(sql1, deptResource1);
			rpm.setDataId(hm.getDataId());
			rpm.setRtnCode("10");
			rpm.setRtnMsg("已经执行完成，接收成功	");
			if (i != 1) {
				rpm.setDataId(hm.getDataId());
				rpm.setRtnCode("40");
				rpm.setRtnMsg("数据有误（合法性错误）");
				log.info("从主数据接收“" + emp.getSname() + "”信息失败");
			} else {
				log.info("从主数据接收“" + emp.getSname() + "”信息成功");
			}

		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("exception", e);
		}
		return tu.resphoneXml(rpm);
	}

	/**
	 * 验证人员在接口表（sa_opperson_saOPorg）是否存在
	 * 
	 * @param organization
	 * @return
	 */
	// @Override
	public int findOpperson(Saopperson emp) {
		bd = new BaseDao();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Integer i = 0;
		try {
			con = bd.getConnection();
			String sql1 = "select count(1) as num from SA_OPPERSON_SAOPORG where sid='" + emp.getSid()
					+ "' and sidcard='" + emp.getSidCard() + "' and sorgkindid='psm'";
			System.out.println(sql1);
			pstm = con.prepareStatement(sql1);

			rs = pstm.executeQuery();
			while (rs.next()) {
				i = Integer.parseInt(rs.getString("num"));
			}
		} catch (SQLException e) {
			log.error("SQLException", e);
		} finally {
			bd.getConnectionClose(con, pstm, rs);
		}
		return i;
	}

	/**
	 * 判断在接口表中是否存在相同的数据
	 * 
	 * @param soo
	 * @return
	 */
	public String findSoo(List<Object> list) {
		bd = new BaseDao();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String validation = "";
		String sfname = "";
		String departmentName = "";
		Saoporg dept = new Saoporg();
		Saopperson emp = new Saopperson();
		dept = (Saoporg) list.get(1);
		emp = (Saopperson) list.get(0);
		try {
			con = bd.getConnection();
			String sql = "select * from sa_opperson_saoporg where sid='" + emp.getSid() + "' and sidcard='"
					+ emp.getSidCard() + "' and sorgkindid='psm'";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				sfname = rs.getString("sfname");
				departmentName = rs.getString("departmentname");

			}
			if (sfname.equals(dept.getOrganization()) && departmentName.equals(dept.getDepartment())) {
				validation = "姓名：" + emp.getSname() + "，身份证：" + emp.getSidCard() + "的人员信息存在相同的数据，无须重复添加";

			}
		} catch (Exception e) {
			log.error("exception", e);
		} finally {
			bd.getConnectionClose(con, pstm, rs);
		}
		return validation;
	}

	/**
	 * 验证从主数据接收人员所在公司是否是本系统维护数据
	 */
	public String validationOgn(List<Object> objlist) {
		String validationBool = "true";
		Saoporg dept = new Saoporg();

		dept = (Saoporg) objlist.get(1);

		String organizationID = dept.getOrganizationID();
		String organizationName = dept.getOrganization();

		if ("20000002".equals(organizationID)) {// 主数据公司编码
			organizationName = "国家开发投资公司";// 对应本系统公司名称
		} else if ("20000052".equals(organizationID)) {
			organizationName = "国投电力控股股份有限公司";// 对应本系统公司名称
		} else if ("20000654".equals(organizationID)) {
			organizationName = "国投矿业投资有限公司";// 对应本系统公司名称
		} else if ("20000208".equals(organizationID)) {
			organizationName = "国投交通控股有限公司";// 对应本系统公司名称
		} else if ("20002041".equals(organizationID)) {
			organizationName = "国投生物科技投资有限公司";// 对应本系统公司名称
		} else if ("20000320".equals(organizationID)) {
			organizationName = "国投资本控股有限公司";// 对应本系统公司名称
		} else if ("20000325".equals(organizationID)) {
			organizationName = "国投泰康信托有限公司";// 对应本系统公司名称
		} else if ("20000414".equals(organizationID)) {
			organizationName = "国投安信股份有限公司";// 对应本系统公司名称
		} else if ("20000361".equals(organizationID)) {
			organizationName = "国投财务有限公司";// 对应本系统公司名称
		} else if ("20000631".equals(organizationID)) {
			organizationName = "融实国际控股有限公司";// 对应本系统公司名称
		} else if ("20000456".equals(organizationID)) {
			organizationName = "中国国投高新产业投资公司";// 对应本系统公司名称
		} else if ("20000649".equals(organizationID)) {
			organizationName = "国投创益产业基金管理有限公司";// 对应本系统公司名称
		} else if ("20001672".equals(organizationID)) {
			organizationName = "国投健康产业投资有限公司";// 对应本系统公司名称
		} else if ("20000646".equals(organizationID)) {
			organizationName = "国投资产管理公司";// 对应本系统公司名称
		} else if ("20000493".equals(organizationID)) {
			organizationName = "中国电子工程设计院";// 对应本系统公司名称
		} else if ("20000645".equals(organizationID)) {
			organizationName = "中投咨询有限公司";// 对应本系统公司名称
		} else if ("20000364".equals(organizationID)) {
			organizationName = "国投物业有限责任公司";// 对应本系统公司名称
		} else if ("20000365".equals(organizationID)) {
			organizationName = "国投物业北京一分公司";// 对应本系统公司名称
		} else if ("20000366".equals(organizationID)) {
			organizationName = "国投物业北京二分公司";// 对应本系统公司名称
		} else if ("20000367".equals(organizationID)) {
			organizationName = "国投物业北京三分公司";// 对应本系统公司名称
		} else if ("20000368".equals(organizationID)) {
			organizationName = "国投物业上海分公司";// 对应本系统公司名称
		} else if ("20000369".equals(organizationID)) {
			organizationName = "五分公司";// 对应本系统公司名称
		} else if ("20000622".equals(organizationID)) {
			organizationName = "中国成套设备进出口（集团）总公司";// 对应本系统公司名称
		} else if ("20000590".equals(organizationID)) {
			organizationName = "中成国际糖业股份有限公司";// 对应本系统公司名称
		} else if ("20000568".equals(organizationID)) {
			organizationName = "中成进出口股份有限公司";// 对应本系统公司名称
		} else if ("20001669".equals(organizationID)) {
			organizationName = "国投智能科技有限公司";// 对应本系统公司名称
		} else if ("20001673".equals(organizationID)) {
			organizationName = "广州国投悦康美邸养老服务有限公司";// 对应本系统公司名称
		} else if ("20000266".equals(organizationID)) {
			organizationName = "国投高科技投资有限公司";// 对应本系统公司名称
		} else if ("20000829".equals(organizationID)) {
			organizationName = "国投海外投资有限公司";// 对应本系统公司名称
		}
		/*
		 * else if ("20000650".equals(organizationID)) { organizationName =
		 * "国投惠康投资有限公司";// 对应本系统公司名称 }
		 */
		else if ("20000856".equals(organizationID)) {
			organizationName = "国投颐康（北京）养老投资有限公司";// 对应本系统公司名称
		} else if ("20000855".equals(organizationID)) {
			organizationName = "国投悦康养老服务有限公司";// 对应本系统公司名称
		} else if ("20000632".equals(organizationID)) {
			organizationName = "国投融资租赁有限公司";// 对应本系统公司名称
		} else if ("20001864".equals(organizationID)) {
			organizationName = "国投微藻生物科技中心";// 对应本系统公司名称
		} else if ("20002575".equals(organizationID)) {
			organizationName = "国投生物能源（铁岭）有限公司";// 对应本系统公司名称
		} else {
			validationBool = "false";
		}
		dept.setOrganization(organizationName);

		return validationBool;
	}

	public String validationOgnImport(Sa_Opperson_Oporg soo) {
		String validationBool = "true";

		String organizationID = soo.getSfid();
		String organizationName = soo.getSfname();

		if ("20000002".equals(organizationID)) {// 主数据公司编码
			organizationName = "国家开发投资公司";// 对应本系统公司名称
		} else if ("20000052".equals(organizationID)) {
			organizationName = "国投电力控股股份有限公司";// 对应本系统公司名称
		} else if ("20000654".equals(organizationID)) {
			organizationName = "国投矿业投资有限公司";// 对应本系统公司名称
		} else if ("20000208".equals(organizationID)) {
			organizationName = "国投交通控股有限公司";// 对应本系统公司名称
		} else if ("20002041".equals(organizationID)) {
			organizationName = "国投生物科技投资有限公司";// 对应本系统公司名称
		} else if ("20000320".equals(organizationID)) {
			organizationName = "国投资本控股有限公司";// 对应本系统公司名称
		} else if ("20000325".equals(organizationID)) {
			organizationName = "国投泰康信托有限公司";// 对应本系统公司名称
		} else if ("20000414".equals(organizationID)) {
			organizationName = "国投安信股份有限公司";// 对应本系统公司名称
		} else if ("20000361".equals(organizationID)) {
			organizationName = "国投财务有限公司";// 对应本系统公司名称
		} else if ("20000631".equals(organizationID)) {
			organizationName = "融实国际控股有限公司";// 对应本系统公司名称
		} else if ("20000456".equals(organizationID)) {
			organizationName = "中国国投高新产业投资公司";// 对应本系统公司名称
		} else if ("20000649".equals(organizationID)) {
			organizationName = "国投创益产业基金管理有限公司";// 对应本系统公司名称
		} else if ("20001672".equals(organizationID)) {
			organizationName = "国投健康产业投资有限公司";// 对应本系统公司名称
		} else if ("20000646".equals(organizationID)) {
			organizationName = "国投资产管理公司";// 对应本系统公司名称
		} else if ("20000493".equals(organizationID)) {
			organizationName = "中国电子工程设计院";// 对应本系统公司名称
		} else if ("20000645".equals(organizationID)) {
			organizationName = "中投咨询有限公司";// 对应本系统公司名称
		} else if ("20000364".equals(organizationID)) {
			organizationName = "国投物业有限责任公司";// 对应本系统公司名称
		} else if ("20000365".equals(organizationID)) {
			organizationName = "国投物业北京一分公司";// 对应本系统公司名称
		} else if ("20000366".equals(organizationID)) {
			organizationName = "国投物业北京二分公司";// 对应本系统公司名称
		} else if ("20000367".equals(organizationID)) {
			organizationName = "国投物业北京三分公司";// 对应本系统公司名称
		} else if ("20000368".equals(organizationID)) {
			organizationName = "国投物业上海分公司";// 对应本系统公司名称
		} else if ("20000369".equals(organizationID)) {
			organizationName = "五分公司";// 对应本系统公司名称
		} else if ("20000622".equals(organizationID)) {
			organizationName = "中国成套设备进出口（集团）总公司";// 对应本系统公司名称
		} else if ("20000590".equals(organizationID)) {
			organizationName = "中成国际糖业股份有限公司";// 对应本系统公司名称
		} else if ("20000568".equals(organizationID)) {
			organizationName = "中成进出口股份有限公司";// 对应本系统公司名称
		} else if ("20001669".equals(organizationID)) {
			organizationName = "国投智能科技有限公司";// 对应本系统公司名称
		} else if ("20001673".equals(organizationID)) {
			organizationName = "广州国投悦康美邸养老服务有限公司";// 对应本系统公司名称
		} else if ("20000266".equals(organizationID)) {
			organizationName = "国投高科技投资有限公司";// 对应本系统公司名称
		} else if ("20000829".equals(organizationID)) {
			organizationName = "国投海外投资有限公司";// 对应本系统公司名称
		}
		/*
		 * else if ("20000650".equals(organizationID)) { organizationName =
		 * "国投惠康投资有限公司";// 对应本系统公司名称 }
		 */
		else if ("20000856".equals(organizationID)) {
			organizationName = "国投颐康（北京）养老投资有限公司";// 对应本系统公司名称
		} else if ("20000855".equals(organizationID)) {
			organizationName = "国投悦康养老服务有限公司";// 对应本系统公司名称
		} else if ("20000632".equals(organizationID)) {
			organizationName = "国投融资租赁有限公司";// 对应本系统公司名称
		} else if ("20001864".equals(organizationID)) {
			organizationName = "国投微藻生物科技中心";// 对应本系统公司名称
		} else if ("20002575".equals(organizationID)) {
			organizationName = "国投生物能源（铁岭）有限公司";// 对应本系统公司名称
		} else {
			validationBool = "false";
		}
		soo.setSfname(organizationName);

		return validationBool;
	}
}
