package com.importInfo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import com.dao.EmpDeptDao;
import com.dao.impl.EmpDeptDaoImpl;
import com.entity.Sa_Opperson_Oporg;
import com.importInfo.ImportName;
import com.util.BaseDao;
import com.util.ImportPersonInfo;

public class ImportNameImpl implements ImportName {
	BaseDao bd = null;
	Logger log = ImportPersonInfo.log;

	public void importNameInfo() {
		bd = new BaseDao();
		log.info("++++++++++++++");
		log.info("++++++++++++++");
		log.info("++++++++++++++");
		log.info("++++++++++++++");
		Sa_Opperson_Oporg soo = new Sa_Opperson_Oporg();
		EmpDeptDao empdeptdao = new EmpDeptDaoImpl();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstm = null;
		// 添加人员之前先判断是否有部门为进行添加
		ImportOgnDepImpl iod = new ImportOgnDepImpl();
		// 添加部门信息
		iod.importOgnDptInfo();
		try {
			con = bd.getConnection();
			// 查询从主数据接收人员信息为未导入swzc系统的状态进行导入
			String selectSql = "select * from sa_opperson_saOporg where zsjstate=0 and sorgkindid='psm' and sfid<>'20000493' order by personmechid asc";
			pstm = con.prepareStatement(selectSql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				
				soo.setSid(rs.getString("sid"));
				soo.setSname(rs.getString("sname"));
				soo.setScode(rs.getString("snameid"));
				soo.setSfname(rs.getString("sfname"));
				soo.setSfid(rs.getString("sfid"));
				soo.setDepartmentid(rs.getString("departmentid"));
				soo.setDepartmentname(rs.getString("departmentname"));
				soo.setSorgkindid(rs.getString("sorgkindid"));
				soo.setSparent(rs.getString("sparent"));
				soo.setSidcode(rs.getString("sidcard"));
				soo.setSvalidstate(rs.getString("svalidstate"));
				soo.setSsex(rs.getString("ssex"));
				soo.setSbirthday(rs.getString("sbirthday"));
				soo.setSjoindate(rs.getString("sjoindate"));
				soo.setSmobilephone(rs.getString("smobilephone"));
				soo.setSofficephone(rs.getString("sofficephone"));
				soo.setPersonMechId(rs.getString("personmechid"));
				soo.setArrt1(rs.getString("arrt1"));
				log.info("===============同步人员信息============");
				log.info("开始执行人员同步任务,同步人员信息：");
				log.info("==Sname：" + soo.getSname());
				log.info("==Sid:" + soo.getSid());
				log.info("==SidCard:" + soo.getSidcode());
				log.info("==sfname:" + soo.getSfname());
				log.info("==deparentName:" + soo.getDepartmentname());
				log.info("==Arrt2:"+soo.getArrt1());
				// 验证人员公司是否是本系统维护公司
				String validationOgn = empdeptdao.validationOgnImport(soo);
				if (validationOgn == "false") {
					updatePsmSvaliState(soo, "-4");
					log.info("合法性错误（’" + soo.getSname() + "“人员所在的“" + soo.getSfname() + "”未录入到实物资产系统中）");

				} else {
					// insertName(soo);
					insertNameEnd(soo);
				}
			}
			if (soo.getSid() == null || soo.getSid().equals("")) {
				log.info("无人员信息添加");
				log.info("=============同步人员信息结束=============");
			}
		} catch (SQLException e) {
			log.error("SQLException", e);
		} finally {
			try {
				bd.getConnectionClose(con, pstm, rs);
			} catch (Exception e2) {
				log.error("Exception", e2);
			}
		}

	}

	public void insertNameEnd(Sa_Opperson_Oporg soo) {
		bd = new BaseDao();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ResultSet rs1=null;
		// 判断状态
		if (soo.getSvalidstate().equals("在岗") || soo.getSvalidstate().equals("在岗在编")) {
			soo.setSvalidstate("1");
		} else {
			soo.setSvalidstate("0");
		}
		String birthday = "";
		String sidCode = soo.getSidcode();
		// 获取人员身份证号
		if (sidCode.length() == 18) {
			String year = sidCode.substring(6, 10);// 截取年
			String month = sidCode.substring(10, 12);// 截取月份
			String day = sidCode.substring(12, 14);// 截取天
			birthday = year + "/" + month + "/" + day;
			log.info("根据身份证：" + sidCode + "获取出生日期:" + birthday);
		} else {
			log.info("身份证不足18位，同步人员信息失败:" + sidCode);
			updatePsmSvaliState(soo, "-2");
			return;
		}
		// 获取当前日期
		Date dd = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String nowTime = sdf.format(dd);
		String deptSid = "";
		String sfname = "";
		String sfcode = "";
		String sfid = "";
		String oporgSid = "";
		String sid = "";
		String scode="";
		try {
			con = bd.getConnection();
			con.setAutoCommit(false);
			String zsjState = "";
			// 查询当前人员是否存在
			zsjState = findOpperson(soo);

			if ("true".equals(zsjState)) {
				// 根据兼职信息判断此人是否在物业
				String sfnameArrt1 = "";
				String arrt1 = "";
				boolean bool = false;
				boolean bool1=false;
				String selectArrte1 = "select sfname,arrt1 from sa_opperson_saoporg where sid='" + soo.getSid() + "' order by arrt1";
				pstm = con.prepareStatement(selectArrte1);
				rs = pstm.executeQuery();
				String arrtRevoke = "";
				while (rs.next()) {
					sfnameArrt1 = rs.getString("sfname");
					arrt1 = rs.getString("arrt1");
					log.info(sfnameArrt1 + "---" + arrt1);
					if ("Y".equals(soo.getArrt1())) {
						if (sfnameArrt1.contains("物业") && "N".equals(arrt1)) {
							arrtRevoke = "主关系在物业";
							bool = true;
							break;
						}
					}
					if ("N".equals(soo.getArrt1())) {
						if (!sfnameArrt1.contains("物业") && "N".equals(arrt1)&&bool1==false) {
							arrtRevoke = "主关系不在物业";
							bool = true;
							break;
						}else {
							String selectArrte2 = "select count(0) as num from sa_opperson_saoporg where sid='" + soo.getSid() + "' and arrt1='Y' ";
							
							pstm = con.prepareStatement(selectArrte2);
							rs1 = pstm.executeQuery();
							if(rs1.next()){
								String num=rs1.getString("num");

								if("0".equals(num)){
									bool=true;
								}else {
									bool=false;
								}
							}
						}
					}
					if ("Y".equals(soo.getArrt1())) {
						if (!sfnameArrt1.contains("物业")) {
							arrtRevoke = "主关系不在物业";
						}
					}
				}
				if (bool) {
					log.info("---"+arrtRevoke+",进行人员信息修改。");
				} else {
					log.info(soo.getSfname() + "---" + soo.getArrt1());
					log.info("人员信息为" + soo.getArrt1() + ":" + arrtRevoke + "不进行人员同步");
					updatePsmSvaliState(soo, "10");
					return;
				}
				log.info("-------人员信息已存在，修改人员信息");
				// 判断人员,公司,部门是否一致
				zsjState = findOgnName(soo);
				
				if ("2".equals(zsjState)) {
					log.info("人员公司不一致");

				} else if ("3".equals(zsjState)) {
					log.info("人员 部门不一致");

				} else if ("1".equals(zsjState)) {
					log.info("人员信息一致");
					updatePsmSvaliState(soo, "2");
					return;
				}
				String selectPersonIDSql = "select SID,SCODE from sa_opperson where sname='" + soo.getSname()
						+ "' and sidcard='" + soo.getSidcode() + "'";
				pstm = con.prepareStatement(selectPersonIDSql);
				rs = pstm.executeQuery();
				while (rs.next()) {
					sid = rs.getString("SID");
					scode=rs.getString("scode");
					
					if (sid.equals(soo.getSid())) {
						sid = soo.getSid();
					}
					if(scode.equals(soo.getScode())){
						scode=soo.getScode();
					}
				}
				if ("".equals(sid) || null == sid) {
					return;
				}
				// 查询公司信息
				String ongInfo[] = findOngInfo(soo);
				sfname = ongInfo[0];
				sfcode = ongInfo[1];
				sfid = ongInfo[2];
				oporgSid = ongInfo[3];
				if (ongInfo[4] == "false") {
					log.info(soo.getSfname() + "有重复的公司名称");
					return;
				}
				if (oporgSid == null || oporgSid == "") {
					log.info("未查询到[" + soo.getSname() + "]所在的[" + soo.getSfname() + "]公司");
					zsjState = "7";
					updatePsmSvaliState(soo, zsjState);
					return;
				}
				if (soo.getDepartmentname() != null && soo.getSorgkindid().equals("psm")) {
					// 查询部门信息
					deptSid = findDeptInfo(soo, oporgSid);
					
					StringBuffer[] result = new StringBuffer[5];
					if (deptSid == null || deptSid == "") {
						// 如果添加的是二级部门人员则执行此方法
						result = insertInfo2(soo);

						if (result[1] == null) {
							log.info("未查询到[" + soo.getSname() + "]所在公司[" + soo.getSfname() + "]的["
									+ soo.getDepartmentname() + "]部门");
							zsjState = "8";
							updatePsmSvaliState(soo, zsjState);
							log.info("修改人员失败");
							return;
						}
					}
					if (deptSid == "") {
						// 拼接组织机构信息result[1]/result[0]:部门Name。result[3]/result[4]:部门ID
						deptSid = result[4].toString();
						sfname = sfname + "/" + result[1] + "/" + result[0] + "/" + soo.getSname();
						sfcode = sfcode + "/" + result[3] + "/" + result[4] + "/" + scode;
						sfid = sfid + "/" + result[3] + ".dpt/" + result[4] + ".dpt/" + sid + "@" + deptSid + ".psm";
					} else {
						// 拼接组织机构信息
						sfname = sfname + "/" + soo.getDepartmentname() + "/" + soo.getSname();
						sfcode = sfcode + "/" + deptSid + "/" + scode;
						sfid = sfid + "/" + deptSid + ".dpt/" + sid + "@" + deptSid + ".psm";
					}
				} else if (soo.getDepartmentname() == null
						|| soo.getDepartmentname().equals("") && soo.getSorgkindid().equals("psm")) {
					deptSid = oporgSid;
					sfname = sfname + "/" + soo.getSname();
					sfcode = sfcode + "/" + scode;
					sfid = sfid + "/" + sid + "@" + deptSid + ".psm";
				}
				
				if(null==deptSid||"".equals(deptSid)){
					zsjState = "8";
					updatePsmSvaliState(soo, zsjState);
					log.info("deptSid为null无法进行人员表部门字段SMAINORGID的更新");
					return;
				}
				log.info("开始修改部门表信息");
				log.info("==Dept:" + soo.getDepartmentname());
				log.info("==Sfname:" + soo.getSfname());
				String updateSaOporg = "update sa_oporg set svalidstate='0' where spersonid='" + sid
						+ "' and svalidstate='1'";
				pstm = con.prepareStatement(updateSaOporg);
				int y = pstm.executeUpdate();
				if (y == 0) {
					// 如果本系统人员组织机构状态为0，主数据又推过来此人的信息，是否还重新添加人员信息。
				}
				String updateParentSql = "update sa_opperson set smainorgid='" + deptSid
						+ "',svalidstate='1',sjoindate= to_date(substr('"+soo.getSjoindate()+"',1,10),'yyyy-mm-dd') where sid='" + sid + "'";
				
				pstm = con.prepareStatement(updateParentSql);
				int k = pstm.executeUpdate();
				if (k == 0) {
					// 如果本系统人员不存在
				}
				String updateZsjSaSql = "update sa_opperson_saoporg set arrt2='" + sfname + "' where personmechid='"
						+ soo.getPersonMechId() + "'";
				pstm = con.prepareStatement(updateZsjSaSql);
//				int j = pstm.executeUpdate();
				String addSql = "insert into sa_oporg(sid,sname,scode,sfname,sfcode,sfid,sorgkindid,ssequence,"
						+ "svalidstate,sparent,spersonid,snodekind,version,sdescription)"
						+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstm = con.prepareStatement(addSql);

				Object obj3[] = { sid + "@" + deptSid, soo.getSname(), scode, sfname, sfcode, sfid,
						soo.getSorgkindid(), 0, soo.getSvalidstate(), deptSid, sid, "nkLeaf", 0, "ZSJ" };
				for (int i = 0; i < obj3.length; i++) {
					pstm.setObject(i + 1, obj3[i]);
				}
				pstm.executeUpdate();
				String updateSql = "update SA_OPPERSON_SAOPORG set zsjstate='6',modifytime=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') where sid='"
						+ soo.getSid() + "' and personmechid='" + soo.getPersonMechId() + "'";
				pstm = con.prepareStatement(updateSql);
				pstm.executeUpdate();

				log.info("人员信息已存在更改成功");
			} else {
				log.info("-------人员信息不存在，同步人员信息");
				/*String selectArrte1 = "select sfname,arrt1 from sa_opperson_saoporg where sid='" + soo.getSid() + "'";
				pstm = con.prepareStatement(selectArrte1);
				rs = pstm.executeQuery();
				System.out.println(selectArrte1);
				String sfnameArrt1 = "";
				String arrt1 = "";
				String arrtRevoke = "";
				boolean bool = false;
				
				while (rs.next()) {
					sfnameArrt1 = rs.getString("sfname");
					arrt1 = rs.getString("arrt1");
					System.out.println(sfnameArrt1 + "---" + arrt1);
					if ("Y".equals(soo.getArrt1())) {
						if (sfnameArrt1.contains("物业") && "N".equals(arrt1)) {
							arrtRevoke = "主关系在物业";
							
							bool = true;
						}
					}
					if ("N".equals(soo.getArrt1())) {
						if (sfnameArrt1.contains("物业") && "N".equals(arrt1)) {
							arrtRevoke = "主关系在物业,且没有兼职信息";
							bool = true;
						}
						
					}
					if ("N".equals(soo.getArrt1())) {
						if (!sfnameArrt1.contains("物业") && "N".equals(arrt1)) {
							arrtRevoke = "主关系不在物业";
							bool = true;
						}
					}
					
					if ("Y".equals(soo.getArrt1())) {
						if (!sfnameArrt1.contains("物业")) {
							arrtRevoke = "主关系不在物业";
						}
					}
				}
				if (bool) {
					System.out.println(soo.getSfname() + "物业人员状态为" + soo.getArrt1());
				} else {
					System.out.println(soo.getSfname() + "---" + soo.getArrt1());
					log.info("人员信息为" + soo.getArrt1() + ":" + arrtRevoke + "不进行人员同步");
					updatePsmSvaliState(soo, "10");
					return;
				}*/
				// 查询公司信息
				String ongInfo[] = findOngInfo(soo);
				sfname = ongInfo[0];
				sfcode = ongInfo[1];
				sfid = ongInfo[2];
				oporgSid = ongInfo[3];
				if (ongInfo[4] == "false") {
					log.info(soo.getSfname() + "有重复的公司名称");
					return;
				}
				if (oporgSid == null || oporgSid == "") {
					log.info("未查询到[" + soo.getSname() + "]所在的[" + soo.getSfname() + "]公司");
					zsjState = "7";
					updatePsmSvaliState(soo, zsjState);
					return;
				}
				// 判断人员是否是组织机构下的人员
				if (soo.getDepartmentname() != null && soo.getSorgkindid().equals("psm")) {
					// 查询部门信息
					deptSid = findDeptInfo(soo, oporgSid);
					StringBuffer[] result = new StringBuffer[5];
					if (deptSid == null || deptSid == "") {
						// 如果添加的是二级部门人员则执行此方法
						result = insertInfo2(soo);

						if (result[1] == null) {
							log.info("未查询到[" + soo.getSname() + "]所在公司[" + soo.getSfname() + "]的"
									+ soo.getDepartmentname() + "部门");
							zsjState = "8";
							updatePsmSvaliState(soo, zsjState);
							return;
						}
					}
					if (deptSid == "") {
						// 拼接组织机构信息result[1]/result[0]:部门Name。result[3]/result[4]:部门ID
						deptSid = result[4].toString();
						sfname = sfname + "/" + result[1] + "/" + result[0] + "/" + soo.getSname();
						sfcode = sfcode + "/" + result[3] + "/" + result[4] + "/" + soo.getScode();
						sfid = sfid + "/" + result[3] + ".dpt/" + result[4] + ".dpt/" + soo.getSid() + "@" + deptSid
								+ ".psm";
					} else {
						// 拼接组织机构信息
						sfname = sfname + "/" + soo.getDepartmentname() + "/" + soo.getSname();
						sfcode = sfcode + "/" + deptSid + "/" + soo.getScode();
						sfid = sfid + "/" + deptSid + ".dpt/" + soo.getSid() + "@" + deptSid + ".psm";
					}
				} else if (soo.getDepartmentname() == null
						|| soo.getDepartmentname().equals("") && soo.getSorgkindid().equals("psm")) {
					deptSid = oporgSid;
					sfname = sfname + "/" + soo.getSname();
					sfcode = sfcode + "/" + soo.getScode();
					sfid = sfid + "/" + soo.getSid() + "@" + deptSid + ".psm";
				}
				log.info("开始新增部门表信息");
				log.info("==Dept:" + soo.getDepartmentname());
				log.info("==Sfname:" + soo.getSfname());
				String insertSql2 = "insert into sa_oporg(sid,sname,scode,sfname,sfcode,sfid,sorgkindid,ssequence,"
						+ "svalidstate,sparent,spersonid,snodekind,version,sdescription)"
						+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstm = con.prepareStatement(insertSql2);

				Object obj3[] = { soo.getSid() + "@" + deptSid, soo.getSname(), soo.getScode(), sfname, sfcode, sfid,
						soo.getSorgkindid(), 0, soo.getSvalidstate(), deptSid, soo.getSid(), "nkLeaf", 0, "ZSJ" };
				for (int i = 0; i < obj3.length; i++) {
					pstm.setObject(i + 1, obj3[i]);
				}
				pstm.executeUpdate();
				log.info("人员部门表信息添加成功");
				log.info("开始新增人员表信息" + soo.getSname());
				String insertSql = "insert into sa_opperson(sid,sname,scode,sidcard,snumb,sloginname,spassword,spasswordmodifytime"
						+ ",smainorgid,ssequence,svalidstate,ssex,sbirthday,sjoindate,smobilephone,sofficephone,scity,version)"
						+ " values(?,?,?,?,?,?,?,to_date(?,'yyyy/MM/dd'),?,?,?,?,to_date(?,'yyyy/MM/dd'),to_date(substr(?,1,10),'yyyy-mm-dd'),?,?,?,?)";
				pstm = con.prepareStatement(insertSql);
				Object obj1[] = { soo.getSid(), soo.getSname(), soo.getScode(), soo.getSidcode(), 6000, soo.getScode(),
						"9A84EE41AA72DE59C63006AAD670BCCE", nowTime, deptSid, 0, soo.getSvalidstate(), soo.getSsex(),
						birthday, soo.getSjoindate(), soo.getSmobilephone(), soo.getSofficephone(), "ZSJ", "0" };
				for (int i = 0; i < obj1.length; i++) {
					pstm.setObject(i + 1, obj1[i]);
				}
				pstm.executeUpdate();
				// 修改状态为1已经导入实物资产
				String updateSql = "update SA_OPPERSON_SAOPORG set zsjstate='1',modifytime=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') where sid='"
						+ soo.getSid() + "' and personmechid='" + soo.getPersonMechId() + "'";
				pstm = con.prepareStatement(updateSql);
				pstm.executeUpdate();
				log.info("新增人员表信息成功");
			}

			con.commit();
		} catch (Exception e) {
			try {
				con.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {

		}
	}

	public void insertName(Sa_Opperson_Oporg soo) {
		bd = new BaseDao();
		Connection con = null;
		PreparedStatement pstm = null;
		// 判断状态
		if (soo.getSvalidstate().equals("在岗") || soo.getSvalidstate().equals("在岗在编")) {
			soo.setSvalidstate("1");
		} else {
			soo.setSvalidstate("0");
		}
		String birthday = "";
		String sidCode = soo.getSidcode();
		// 获取人员身份证号
		if (sidCode.length() == 18) {
			String year = sidCode.substring(6, 10);// 截取年
			String month = sidCode.substring(10, 12);// 截取月份
			String day = sidCode.substring(12, 14);// 截取天
			birthday = year + "/" + month + "/" + day;
			log.info("根据身份证：" + sidCode + "获取出生日期:" + birthday);
		} else {
			log.info("身份证不足18位，同步人员信息失败:" + sidCode);
			updatePsmSvaliState(soo, "-2");
			return;
		}
		// 获取当前日期
		Date dd = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String nowTime = sdf.format(dd);
		try {
			con = bd.getConnection();
			con.setAutoCommit(false);
			String zsjState = "";

			// 查询当前人员是否存在
			zsjState = findOpperson(soo);
			// 判断人员信息是否存在 如果已经存在则进行修改 如不存在则进行人员添加
			if (zsjState.equals("true")) {
				zsjState = findOgnName(soo);
				String stateName = soo.getSvalidstate();
				if ("离职".equals(stateName) || "解除劳务合同".equals(soo.getSvalidstate())) {
					zsjState = "5";
				} else if ("退休".equals(soo.getSvalidstate()) || "内退".equals(soo.getSvalidstate())) {
					zsjState = "4";
				}
				updatePsmSvaliState(soo, zsjState);
			} else {
				String deptSid = "";
				String sfname = "";
				String sfcode = "";
				String sfid = "";
				String oporgSid = "";
				// 判断人员信息登陆名在本系统是否存在
				if (!"".equals(loginNameValiState(soo.getScode()))) {
					updatePsmSvaliState(soo, "9");
					log.info(soo.getSname() + "人员登陆名(" + soo.getScode() + ")在本系统已存在");
					return;
				}
				// 查询公司信息
				String ongInfo[] = findOngInfo(soo);
				sfname = ongInfo[0];
				sfcode = ongInfo[1];
				sfid = ongInfo[2];
				oporgSid = ongInfo[3];
				if (ongInfo[4] == "false") {
					log.info(soo.getSfname() + "有重复的公司名称");
					return;
				}
				if (oporgSid == null || oporgSid == "") {
					log.info("未查询到[" + soo.getSname() + "]所在的[" + soo.getSfname() + "]公司");
					zsjState = "7";
					updatePsmSvaliState(soo, zsjState);
					return;
				}
				// 判断人员是否是组织机构下的人员
				if (soo.getDepartmentname() != null && soo.getSorgkindid().equals("psm")) {
					// 查询部门信息
					deptSid = findDeptInfo(soo, oporgSid);
					StringBuffer[] result = new StringBuffer[5];
					if (deptSid == null || deptSid == "") {
						// 如果添加的是二级部门人员则执行此方法
						result = insertInfo2(soo);

						if (result[1] == null) {
							log.info("未查询到[" + soo.getSname() + "]所在公司[" + soo.getSfname() + "]的"
									+ soo.getDepartmentname() + "部门");
							zsjState = "8";
							updatePsmSvaliState(soo, zsjState);
							return;
						}
					}
					if (deptSid == "") {
						// 拼接组织机构信息result[1]/result[0]:部门Name。result[3]/result[4]:部门ID
						deptSid = result[4].toString();
						sfname = sfname + "/" + result[1] + "/" + result[0] + "/" + soo.getSname();
						sfcode = sfcode + "/" + result[3] + "/" + result[4] + "/" + soo.getScode();
						sfid = sfid + "/" + result[3] + ".dpt/" + result[4] + ".dpt/" + soo.getSid() + "@" + deptSid
								+ ".psm";
					} else {
						// 拼接组织机构信息
						sfname = sfname + "/" + soo.getDepartmentname() + "/" + soo.getSname();
						sfcode = sfcode + "/" + deptSid + "/" + soo.getScode();
						sfid = sfid + "/" + deptSid + ".dpt/" + soo.getSid() + "@" + deptSid + ".psm";
					}
				} else if (soo.getDepartmentname() == null
						|| soo.getDepartmentname().equals("") && soo.getSorgkindid().equals("psm")) {
					deptSid = oporgSid;
					sfname = sfname + "/" + soo.getSname();
					sfcode = sfcode + "/" + soo.getScode();
					sfid = sfid + "/" + soo.getSid() + "@" + deptSid + ".psm";
				}
				log.info("开始新增部门表信息");
				log.info("==Dept:" + soo.getDepartmentname());
				log.info("==Sfname:" + soo.getSfname());
				String insertSql2 = "insert into sa_oporg(sid,sname,scode,sfname,sfcode,sfid,sorgkindid,ssequence,"
						+ "svalidstate,sparent,spersonid,snodekind,version,sdescription)"
						+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstm = con.prepareStatement(insertSql2);

				Object obj3[] = { soo.getSid() + "@" + deptSid, soo.getSname(), soo.getScode(), sfname, sfcode, sfid,
						soo.getSorgkindid(), 0, soo.getSvalidstate(), deptSid, soo.getSid(), "nkLeaf", 0, "ZSJ" };
				for (int i = 0; i < obj3.length; i++) {
					pstm.setObject(i + 1, obj3[i]);
				}
				pstm.executeUpdate();
				log.info("人员部门表信息添加成功");
				log.info("开始新增人员表信息" + soo.getSname());
				String insertSql = "insert into sa_opperson(sid,sname,scode,sidcard,snumb,sloginname,spassword,spasswordmodifytime"
						+ ",smainorgid,ssequence,svalidstate,ssex,sbirthday,sjoindate,smobilephone,sofficephone,scity,version)"
						+ " values(?,?,?,?,?,?,?,to_date(?,'yyyy/MM/dd'),?,?,?,?,to_date(?,'yyyy/MM/dd'),to_date(substr(?,1,10),'yyyy-mm-dd'),?,?,?,?)";
				pstm = con.prepareStatement(insertSql);
				Object obj1[] = { soo.getSid(), soo.getSname(), soo.getScode(), soo.getSidcode(), 6000, soo.getScode(),
						"9A84EE41AA72DE59C63006AAD670BCCE", nowTime, deptSid, 0, soo.getSvalidstate(), soo.getSsex(),
						birthday, soo.getSjoindate(), soo.getSmobilephone(), soo.getSofficephone(), "ZSJ", "0" };
				for (int i = 0; i < obj1.length; i++) {
					pstm.setObject(i + 1, obj1[i]);
				}
				pstm.executeUpdate();
				// 修改状态为1已经导入实物资产
				String updateSql = "update SA_OPPERSON_SAOPORG set zsjstate='1',modifytime=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') where sid='"
						+ soo.getSid() + "' and personmechid='" + soo.getPersonMechId() + "'";
				pstm = con.prepareStatement(updateSql);
				pstm.executeUpdate();
				log.info("新增人员表信息成功");
			}
			con.commit();
		} catch (SQLException e) {
			try {
				log.info(soo.getSname() + "新增失败");
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			log.error("SQLException", e);
		} catch (Exception e) {
			try {
				log.info(soo.getSname() + "新增失败");
				con.rollback();
			} catch (SQLException e1) {
				log.error("exception", e1);
			}
			log.error("exception", e);
		} finally {
			try {
				bd.getConnectionClose(con, pstm, null);

			} catch (Exception e) {
				log.error("SQLException", e);
			}
		}
	}

	// 如果添加的部门是二级部门人员
	public StringBuffer[] insertInfo2(Sa_Opperson_Oporg soo) {
		bd = new BaseDao();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer departmentName = new StringBuffer();
		StringBuffer deptSid = new StringBuffer();
		String sparent = soo.getSparent();
		StringBuffer deptInfo[] = new StringBuffer[5];
		StringBuffer deptInfo1[] = new StringBuffer[5];
		boolean bool = false;
		try {
			con = bd.getConnection();

			int i = 0;
			do {
				bool = false;
				String sql = "select * from SA_OPPERSON_SAOPORG where departmentid='" + sparent
						+ "' and sorgkindid='dpt'";
				
				pstm = con.prepareStatement(sql);
				rs = pstm.executeQuery();
				while (rs.next()) {
					String departmentName1 = rs.getString("departmentname");
					deptInfo[0] = departmentName.insert(0, departmentName1 + "/");
					deptInfo1[i] = new StringBuffer(departmentName1);
					sparent = rs.getString("sparent");
					bool = true;
				}
				i++;
			} while (bool);
			deptInfo1[2] = deptSid.append(soo.getSfname());
			deptInfo1 = findDeptSid(deptInfo1);
			deptInfo[1] = deptInfo1[3];
			deptInfo[2] = deptInfo1[4];

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				bd.getConnectionClose(con, pstm, rs);

			} catch (Exception e2) {
				log.error("SQLException", e2);
			}
		}
		return deptInfo1;
	}

	public StringBuffer[] findDeptSid(StringBuffer deptInfo[]) throws SQLException {
		bd = new BaseDao();
		Connection con = bd.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;

		String orgSid = "";
		String deptSid = "";
		String deptSid2 = "";
		String selectOrgSid2 = "select * from sa_oporg where sname='" + deptInfo[2] + "' and sorgkindid='ogn'";
		pstm = con.prepareStatement(selectOrgSid2);
		rs2 = pstm.executeQuery();
		while (rs2.next()) {
			orgSid = rs2.getString("sid");
		}

		String selectDeptSid1 = "select * from sa_oporg where sname='" + deptInfo[1] + "' and sparent='" + orgSid
				+ "' and sorgkindid='dpt'";
		pstm = null;
		pstm = con.prepareStatement(selectDeptSid1);

		rs1 = pstm.executeQuery();
		while (rs1.next()) {
			deptSid = rs1.getString("sid");
		}

		deptInfo[3] = new StringBuffer(deptSid);
		String selectDeptSid = "select * from sa_oporg where sname='" + deptInfo[0] + "' and sparent='" + deptSid
				+ "' and sorgkindid='dpt'";
		pstm = null;
		pstm = con.prepareStatement(selectDeptSid);
		rs = pstm.executeQuery();
		while (rs.next()) {
			deptSid2 = rs.getString("sid");
		}
		if (deptSid2 == "") {
			log.info("未找到部门" + deptInfo[0]);
		}
		deptInfo[4] = new StringBuffer(deptSid2);
		try {
			if (rs != null) {
				rs.close();
			}
			if (rs1 != null) {
				rs1.close();
			}
			if (rs2 != null) {
				rs2.close();
			}
			bd.getConnectionClose(con, pstm, null);

		} catch (Exception e) {
			log.error("Exception", e);
		}
		return deptInfo;
	}

	/**
	 * 查询人员信息
	 * 
	 * @param soo
	 * @return
	 */
	public String findOpperson(Sa_Opperson_Oporg soo) {
		bd = new BaseDao();
		String result = "";
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = bd.getConnection();
			String selectSql = "select * from sa_opperson where sidcard='"+ soo.getSidcode() + "'";
			pstm = con.prepareStatement(selectSql);
			rs = pstm.executeQuery();
			if (rs.next()) {
				result = "true";
			}
		} catch (SQLException e) {
			log.info(soo.getSname() + "sa_oporg表人员信息查询失败");
			e.printStackTrace();
		} finally {
			bd.getConnectionClose(con, pstm, rs);
		}
		return result;
	}

	// 判断主数据数据与本系统数据（人员姓名，身份证，公司）是否完全在相同
	public String findOgnName(Sa_Opperson_Oporg soo) {
		bd = new BaseDao();
		String result = "";
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs2 = null;
		ResultSet rs = null;
		ResultSet rs3 = null;
		String sorgkindid = "";
		String sparent = "";
		String sparentDept = "";
		String ognName = "";
//		String deptName = "";
		int i = 0;
		try {
			con = bd.getConnection();
			// 查询公司信息
			String selectSql2 = "select sparent from sa_oporg where spersonid=(select sid from sa_opperson where sname='"
					+ soo.getSname() + "' and sidcard='" + soo.getSidcode() + "') and svalidstate='1'";
			
			pstm = con.prepareStatement(selectSql2);
			rs2 = pstm.executeQuery();
			while (rs2.next()) {
				sparent = rs2.getString("sparent");
				i++;
			}
			String selectSql3 = "select sname from sa_oporg where sid='" + sparent + "'";
			pstm = con.prepareStatement(selectSql3);
			rs3 = pstm.executeQuery();
			while (rs3.next()) {
				sparentDept = rs3.getString("sname");
			}
			pstm.close();
			if (i > 1) {
				log.info(soo.getSname() + "sa_opperson存在人员信息大于两条");
				result = "3";
			} else if (sparent != "") {
				while (!"ogn".equals(sorgkindid)) {
					rs = null;
					pstm = null;
					selectSql2 = "select * from sa_oporg where sid='" + sparent + "' and svalidstate='1' ";
					pstm = con.prepareStatement(selectSql2);
					rs = pstm.executeQuery();
					while (rs.next()) {
						sorgkindid = rs.getString("sorgkindid");
						ognName = rs.getString("sname");
						sparent = rs.getString("sparent");
						// deptName = rs.getString("sname");
					}
					if ("".equals(sorgkindid) || null == sorgkindid) {
						log.info("/\n/\n/”" + soo.getSname() + "“所在的部门ID为:" + sparent + "的部门已不存在。/\n/\n/");
						break;
					}
				}
			}
			log.info(sparentDept + "---" + soo.getDepartmentname());
			log.info(ognName + "---" + soo.getSfname());

			if (!sparentDept.equals(soo.getDepartmentname())) {
				result = "3";// 姓名，身份证与本系统一致，部门名与本系统不一致

			}
			if (!ognName.equals(soo.getSfname())) {
				result = "2";// 姓名，身份证,部门与本系统一致，公司名与本系统不一致

			}
			if (ognName.equals(soo.getSfname()) && sparentDept.equals(soo.getDepartmentname())) {
				result = "1";// 姓名，身份证，公司,部门，名与本系统一致

			}
			if ("".equals(sorgkindid) || null == sorgkindid) {
				result = "-1";// 根据人员部门id未找到所在部门信息

			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				if (rs2 != null) {
					rs2.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				bd.getConnectionClose(con, null, rs);

			} catch (SQLException e) {
				log.error("SQLException", e);
			}

		}
		return result;
	}

	public String[] findOngInfo(Sa_Opperson_Oporg soo) {
		bd = new BaseDao();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs2 = null;
		String ongInfo[] = new String[5];
		int i = 0;
		try {
			con = bd.getConnection();
			// 查询公司信息
			String selectSql2 = "select * from sa_oporg where sname='" + soo.getSfname() + "' and sorgkindid='ogn'";
			pstm = con.prepareStatement(selectSql2);
			rs2 = pstm.executeQuery();
			while (rs2.next()) {
				ongInfo[0] = rs2.getString("sfname");// 获取上级组织名称
				ongInfo[1] = rs2.getString("sfcode");// 获取上级组织编码
				ongInfo[2] = rs2.getString("sfid");// 获取上级组织id
				ongInfo[3] = rs2.getString("sid");//// 获取公司id
				i++;
			}
			ongInfo[4] = "";
			if (i > 1) {
				ongInfo[4] = "false";
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			bd.getConnectionClose(con, pstm, rs2);

		}
		return ongInfo;
	}

	// 查询部门信息
	public String findDeptInfo(Sa_Opperson_Oporg soo, String oporgSid) {
		bd = new BaseDao();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs1 = null;
		String deptSid = "";
		try {
			con = bd.getConnection();
			String selectSql1 = "select * from sa_oporg where sname='" + soo.getDepartmentname() + "' and sparent='"
					+ oporgSid + "'";
			pstm = con.prepareStatement(selectSql1);
			rs1 = pstm.executeQuery();
			while (rs1.next()) {
				deptSid = rs1.getString("sid");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			bd.getConnectionClose(con, pstm, rs1);

		}
		return deptSid;
	}

	// 修改人员状态
	public void updatePsmSvaliState(Sa_Opperson_Oporg soo, String zsjState) {
		bd = new BaseDao();
		log.info(soo.getSname() + "开始修改人员状态人员信息");
		try {
			String updateSql = "update SA_OPPERSON_SAOPORG set zsjstate='" + zsjState
					+ "',modifytime=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') where sid='" + soo.getSid()
					+ "' and personmechid='" + soo.getPersonMechId() + "'";
			int i = bd.executeSQL(updateSql, null);
			if (i > 0) {
				log.info(soo.getSname() + "修改状态成功");
			} else {
				log.info(soo.getSname() + "修改状态失败");
			}
		} catch (Exception e) {
			log.error("Exception", e);
		}
	}

	// 验证是否有相同的登陆名
	public String loginNameValiState(String snameid) {
		bd = new BaseDao();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String valiState = "";
		try {
			con = bd.getConnection();
			String selectSql1 = "select * from sa_opperson where sloginname='" + snameid + "'";
			pstm = con.prepareStatement(selectSql1);
			rs = pstm.executeQuery();
			while (rs.next()) {
				valiState = rs.getString("sid");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			bd.getConnectionClose(con, pstm, rs);

		}
		return valiState;
	}
}
