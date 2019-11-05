package com.atguigu.manager.service;

import java.util.Map;

import com.atguigu.bean.Advertisement;
import com.atguigu.utils.Page;

public interface AdvertisementService {

	int saveAdvertisement(Advertisement advertisement);

	Page queryPage(Map<String, Object> map);

	void deleteById(Integer id);

	void deleteBatchByIds(Integer[] ids);

	Advertisement findById(Integer id);

}
