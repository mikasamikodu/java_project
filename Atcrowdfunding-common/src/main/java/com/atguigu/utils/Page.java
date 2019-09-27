package com.atguigu.utils;

import java.util.List;
@SuppressWarnings("rawtypes")
public class Page {

	private Integer pageNo;//当前页码
	private Integer pageSize;//当前页容量
	
	private List datas;//当前页数据
	private Integer totalNo;//页面的数量
	private Integer totalSize;//数据总记录数
	
	public Page(Integer pageNo, Integer pageSize) {
		if(pageNo <= 0) {
			this.pageNo = 1;
		}else {
			this.pageNo = pageNo;
		}
		if(pageSize <= 0) {
			this.pageSize = 10;
		}else {
			this.pageSize = pageSize;
		}
	}
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public List getDatas() {
		return datas;
	}
	public void setDatas(List datas) {
		this.datas = datas;
	}
	public Integer getTotalNo() {
		return totalNo;
	}
	@SuppressWarnings("unused")
	private void setTotalNo(Integer totalNo) {
		this.totalNo = totalNo;
	}
	public Integer getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
		this.totalNo = (totalSize%this.pageSize)==0?(totalSize/this.pageSize):(totalSize/this.pageSize+1);
	}
	public Integer getStartIndex() {
		return (this.pageNo-1) * this.pageSize;
	};
}
