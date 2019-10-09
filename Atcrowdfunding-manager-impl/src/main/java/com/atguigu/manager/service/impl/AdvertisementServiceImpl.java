package com.atguigu.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.Advertisement;
import com.atguigu.manager.dao.AdvertisementMapper;
import com.atguigu.manager.service.AdvertisementService;

@Service("advertisementService")
public class AdvertisementServiceImpl implements AdvertisementService{

	@Autowired
	private AdvertisementMapper advertisementMapper;
	@Override
	public int saveAdvertisement(Advertisement advertisement) {
		return advertisementMapper.insert(advertisement);
	}

}
