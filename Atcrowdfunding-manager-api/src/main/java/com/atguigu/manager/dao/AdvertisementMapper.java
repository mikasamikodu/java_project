package com.atguigu.manager.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.bean.Advertisement;

public interface AdvertisementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Advertisement record);

    Advertisement selectByPrimaryKey(Integer id);

    List<Advertisement> selectAll();

    int updateByPrimaryKey(Advertisement record);

	List<Advertisement> queryPage(Map<String, Object> map);

	Integer queryCount(Map<String, Object> map);

	void deleteBatchByIds(Integer[] ids);
}