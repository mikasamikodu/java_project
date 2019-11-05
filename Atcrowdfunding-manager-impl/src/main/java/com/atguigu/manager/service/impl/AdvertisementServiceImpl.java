package com.atguigu.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.Advertisement;
import com.atguigu.manager.dao.AdvertisementMapper;
import com.atguigu.manager.service.AdvertisementService;
import com.atguigu.utils.Page;

@Service("advertisementService")
public class AdvertisementServiceImpl implements AdvertisementService{

	@Autowired
	private AdvertisementMapper advertisementMapper;
	@Override
	public int saveAdvertisement(Advertisement advertisement) {
		return advertisementMapper.insert(advertisement);
	}
	@Override
	public Page queryPage(Map<String, Object> map) {
		Page page = new Page((Integer)map.get("pageNo"),(Integer)map.get("pageSize"));
		map.put("startIndex", page.getStartIndex());
		List<Advertisement> list = advertisementMapper.queryPage(map);
		page.setDatas(list);
		Integer count = advertisementMapper.queryCount(map);
		page.setTotalSize(count);
		return page;
	}
	@Override
	public void deleteById(Integer id) {
		advertisementMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void deleteBatchByIds(Integer[] ids) {
		advertisementMapper.deleteBatchByIds(ids);
	}
	@Override
	public Advertisement findById(Integer id) {
		return advertisementMapper.selectByPrimaryKey(id);
	}

}
