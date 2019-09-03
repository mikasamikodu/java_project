package com.service.ws.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

//import com.service.ImportCorpService;
import com.service.impl.ImportCorpServiceImpl;
import com.service.ws.importCorp;
import com.util.ImportPersonInfo;

@Service("/importCorp")
public class importCorpImpl implements importCorp {
	protected Logger log = ImportPersonInfo.log;
	ImportCorpServiceImpl ics = new ImportCorpServiceImpl();

	public String importCorpInfo(String requestXml) {
		log.info("***********");
		log.info("***********");
		log.info("***********");
		log.info("======================开始接收组织机构信息================");

		String strInfo = ics.importCorpInfo(requestXml);
		log.info("======================接收结束================");
		log.info("======================返回报文:" + strInfo + "================");
		return strInfo;
	}

}
