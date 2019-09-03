package com.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
//import java.util.UUID;

import org.apache.log4j.Logger;

import com.dao.ImportVendorDao;
import com.entity.HeadManage;
import com.entity.Vendor;
import com.entity.resphoneManage;
import com.util.BaseDao;
import com.util.ImportPoVendorInfo;
//import com.util.ToXmlUtilOgnDept;
import com.util.ToXmlUtilVendor;

public class ImportVendorDaoImpl implements ImportVendorDao {
	Logger log = ImportPoVendorInfo.log;
	BaseDao bd = null;
	ToXmlUtilVendor tu = new ToXmlUtilVendor();
	HeadManage hm = null;
	resphoneManage rpm = new resphoneManage();
	/**
	 *接收供应商信息,插入到接口表zsj_vendor
	 */
	public String importVendorInfo(List<Object> objlist) {
		bd = new BaseDao();
		hm=new HeadManage();
		Vendor vendor = new Vendor();
		vendor = (Vendor) objlist.get(0);		
		hm=(HeadManage) objlist.get(1);
		int num=0;
		try {
			String sql = "insert into zsj_vendor(zsjvendorid,vendor_id,vendor_name,vendor_site_id,vendor_site_code,org_id,"
					+ "org_name,currenttime,zsjstate,arrt1,arrt2,arrt3,arrt4,arrt5,arrt6) "
					+ "values(seq_zsjvendor.nextval,?,?,?,?,?,?,sysdate,0,?,?,?,?,?,?)";
			Object [] obj={vendor.getVendor_id(),vendor.getVendor_name(),vendor.getVendor_site_id(),
					vendor.getVendor_site_name(),vendor.getOrg_id(),vendor.getOrg_name(),vendor.getArrt1(),vendor.getArrt2(),vendor.getArrt3(),vendor.getArrt4(),vendor.getArrt5(),vendor.getArrt6()};
		num=bd.executeSQL(sql, obj);
		if(num>0){
			rpm.setDataId(hm.getDataId());
			rpm.setRtnCode("10");
			rpm.setRtnMsg("已经执行完成，接收成功	");
			log.info("---供应商接收成功");
		}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			if(num<1){
				rpm.setDataId(hm.getDataId());
				rpm.setRtnCode("40");
				rpm.setRtnMsg("数据有误(合法性错误)");
			}
		}	
		return tu.resphoneXml(rpm);
	}
	/**
	 * 查询主数据供应商是否存在重数据
	 * @param vendor
	 * @return
	 */
	public String findVendor(Vendor vendor){
		BaseDao bd=new BaseDao();
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement pstm=null;
		String str="";
		try {
			con=bd.getConnection();
			String sql="select * from zsj_vendor where VENDOR_NAME='"+vendor.getVendor_name()+"' and ORG_ID='"+vendor.getOrg_id()+"'";
			System.out.println(sql);
			pstm=con.prepareStatement(sql);
			rs=pstm.executeQuery(sql);
			if(rs.next()){
				str="true";
			}
		} catch (SQLException e) {
			str="false";
			e.printStackTrace();
		}
		finally{
			bd.getConnectionClose(con, pstm, rs);
		}
		return str;
	}

}
