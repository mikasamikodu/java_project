package com.it.shop.service;

import java.sql.SQLException;
import java.util.List;

import com.it.shop.dao.CommodityDao;
import com.it.shop.domain.Commodity;

public class CommodityService {

	CommodityDao dao = new CommodityDao();
	
	public List<Commodity> findAllCommoditys() {
		try {
			return dao.findAllCommoditys();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
