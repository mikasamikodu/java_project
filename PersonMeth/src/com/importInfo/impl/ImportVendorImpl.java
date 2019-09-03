package com.importInfo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.entity.Vendor;
//import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.util.BaseDao;
//import com.util.ImportPersonInfo;
import com.util.ImportPoVendorInfo;

public class ImportVendorImpl {
	public static void main(String[] args) {
		new ImportVendorImpl().importVendorInfo();
	}
	Logger log = ImportPoVendorInfo.log;
	public void importVendorInfo() {
		log.info("Vendor");
		log.info("Vendor");
		log.info("Vendor");
		log.info("---开始同步供应商");
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		BaseDao bd = new BaseDao();
		Vendor vendor = new Vendor();
		try {
			con = bd.getConnection();
			String sql = "select * from zsj_vendor where zsjState='0'";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				vendor.setArrt1(rs.getString("arrt1"));
				vendor.setArrt2(rs.getString("arrt2"));
				vendor.setArrt3(rs.getString("arrt3"));
				vendor.setArrt4(rs.getString("arrt4"));
				vendor.setArrt5(rs.getString("arrt5"));
				vendor.setArrt6(rs.getString("arrt6"));
				vendor.setOrg_id(rs.getString("org_id"));
				vendor.setOrg_name(rs.getString("org_name"));
				vendor.setVendor_id(rs.getString("vendor_id"));
				vendor.setVendor_name(rs.getString("vendor_name"));
				vendor.setVendor_site_id(rs.getString("vendor_site_id"));
				vendor.setVendor_site_name(rs.getString("VENDOR_SITE_CODE"));
				log.info("---供应商:"+vendor.getVendor_name());
				log.info("---供应商所属公司："+vendor.getOrg_name());
				insertVendor(vendor);
			}
			if(vendor.getVendor_name()==null){
				
				log.info("---无供应商信息同步");
			}
		} catch (SQLException e) {
			log.info("查询同步供应商失败");
			e.printStackTrace();
		} finally {
			bd.getConnectionClose(con, pstm, rs);
		}

	}

	public void insertVendor(Vendor vendor) {
		BaseDao bd = new BaseDao();
		Connection con = null;
		PreparedStatement pstm = null;
		vendor = findOgnID(vendor);
		String str = findPoVendor(vendor);
	
		if ("fals"!=str) {
			log.info("---供应商已存在");
			updateZsjState(vendor,"2");
			return;
		}
		try {
			con = bd.getConnection();
			con.setAutoCommit(false);
			String sql = "insert into po_vendor_site_v(fid,VENDOR_ID,VENDOR_NAME,VENDOR_SITE_ID,VENDOR_SITE_CODE,ORG_ID,scity) values(?,?,?,?,?,?,'ZSJ')";
			Object[] obj = { UUID.randomUUID().toString().replace("-", ""), vendor.getVendor_id(),
					vendor.getVendor_name(), vendor.getVendor_site_id(), vendor.getVendor_site_name(),
					vendor.getOrg_id() };
			pstm = con.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pstm.setObject(i + 1, obj[i]);
			}
			int num = pstm.executeUpdate();

			if (num > 0) {
				updateZsjState(vendor,"1");
				log.info("---供应商同步成功");
			} else {
				updateZsjState(vendor,"3");
				log.info("---供应商同步失败");
			}
			con.commit();

		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				log.info("---同步供应商出错，任务已回滚");
				e1.printStackTrace();
				log.error("SQLException",e1);
			}
			log.error("Exception",e);
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				log.info("---同步供应商数据库联接关闭失败");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据公司名称查询公司id
	 * 
	 * @param vendor
	 * @return
	 */
	public Vendor findOgnID(Vendor vendor) {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		BaseDao bd = new BaseDao();
		try {
			con = bd.getConnection();
			String sql = "select sid from sa_oporg where sname='" + vendor.getOrg_name() + "' and sorgkindid='ogn'";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				vendor.setOrg_id(rs.getString("sid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			bd.getConnectionClose(con, pstm, rs);
		}
		return vendor;
	}

	/**
	 * 查询供应商是否重复
	 * 
	 * @param vendor
	 * @return
	 */
	public String findPoVendor(Vendor vendor) {
		String str = "";
		BaseDao bd = new BaseDao();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = bd.getConnection();
			String sql = "select * from po_vendor_site_v where VENDOR_NAME='" + vendor.getVendor_name()
					+ "' and ORG_ID='" + vendor.getOrg_id() + "'";
			System.out.println(sql);
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			if (rs.next()) {
				str = "true";
			} else {
				str = "fals";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return str;
	}

	public void updateZsjState(Vendor vendor,String zsjState) {
		BaseDao bd = new BaseDao();
		try {
			String updateSql = "update zsj_vendor set zsjstate='"+zsjState+"',modifytime=sysdate where vendor_id='"
					+ vendor.getVendor_id() + "' and org_name='" + vendor.getOrg_name() + "'";
			bd.executeSQL(updateSql, null);
		} catch (Exception e) {
			log.info("修改主数据状态失败");
			e.printStackTrace();
		}
	}
}
