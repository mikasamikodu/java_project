package com.it.shop.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.it.shop.domain.Commodity;
import com.it.shop.util.C3P0Util;

public class CommodityDao {

	public List<Commodity> findAllCommoditys() throws SQLException {
		QueryRunner query = new QueryRunner(C3P0Util.getDatabase());
		String sql = "select * from COMMODITY_INFOM";
		return query.query(sql, new BeanListHandler<Commodity>(Commodity.class));
	}

}
