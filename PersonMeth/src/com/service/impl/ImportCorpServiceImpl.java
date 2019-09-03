package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dao.ImportCorpDao;
import com.dao.impl.ImportCorpDaoImpl;
import com.entity.HeadManage;
import com.entity.Sa_Opperson_Oporg;
import com.entity.resphoneManage;
import com.service.ImportCorpService;
import com.util.DateResultNotNull;
import com.util.ImportPersonInfo;
import com.util.ToXmlUtilOgnDept;

public class ImportCorpServiceImpl implements ImportCorpService {

	Logger log = ImportPersonInfo.log;//为对象的操作记录日志
	
	
	public String importCorpInfo(String requestXml) {
		DateResultNotNull drnn = new DateResultNotNull();
		Sa_Opperson_Oporg soo=new Sa_Opperson_Oporg();//实体类建立一个对象
		ImportCorpDao importCorp = new ImportCorpDaoImpl();
		resphoneManage rpm = new resphoneManage();//实体类建立一个对象
		HeadManage hm = new HeadManage();//实体类建立一个对象
		ToXmlUtilOgnDept txu = new ToXmlUtilOgnDept();
		List<Object> objlist=new ArrayList<Object>();
		String OporgIsNotNull="";
		try {
			// 解析xml,返回相应的验证信息
			objlist = txu.toXml(requestXml);
			log.info("接收报文："+requestXml);		
			hm = (HeadManage) objlist.get(1);
			soo=(Sa_Opperson_Oporg) objlist.get(0);//添加组织机构数据效验
			OporgIsNotNull=drnn.OporgnotNull(soo);
			if (OporgIsNotNull!= "") {//效验返回报文
				rpm.setRtnMsg("合法性错误（" + OporgIsNotNull + "）");
				rpm.setRtnCode("40");
				rpm.setDataId(hm.getDataId());
				log.info("====接收组织机构信息失败");
				return txu.resphoneXml(rpm);
			}
			/*
			//验证该公司是否是实物资产系统维护的公司
			String svalidationOgn=drnn.validationOgn(soo);
			if("false".equals(svalidationOgn)){
				rpm.setRtnMsg("合法性错误（”" + soo.getCompanyname() + "“该公司未获得实物资产录入授权）");
				rpm.setRtnCode("40");
				rpm.setDataId(hm.getDataId());
				log.info("====接收组织机构信息失败");
				return txu.resphoneXml(rpm);
			}*/
		} catch (Exception e) {
			log.error("Exception",e);
			e.printStackTrace();
			rpm.setRtnMsg("xml格式错误");
			rpm.setRtnCode("20");
			rpm.setDataId(hm.getDataId());
			log.info("");
			return txu.resphoneXml(rpm);
		}		
		return importCorp.importCorpInfo(objlist);
	}
}
