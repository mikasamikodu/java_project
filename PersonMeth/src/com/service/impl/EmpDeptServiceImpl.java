package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.dao.EmpDeptDao;
import com.dao.impl.EmpDeptDaoImpl;
import com.entity.Saoporg;
import com.entity.Saopperson;
import com.entity.HeadManage;
import com.entity.resphoneManage;
import com.service.EmpDeptService;
import com.util.DateResultNotNull;
import com.util.ImportPersonInfo;
import com.util.ToXmlUtil;

public class EmpDeptServiceImpl implements EmpDeptService {
	Logger log = ImportPersonInfo.log;

	public String importName(String requestXml) {
		EmpDeptDao empdeptdao = new EmpDeptDaoImpl();
		ToXmlUtil txu = new ToXmlUtil();
		List<Object> objlist = new ArrayList<Object>();
		DateResultNotNull drnn = new DateResultNotNull();
		HeadManage hm = new HeadManage();
		String str = "";

		resphoneManage rpm = new resphoneManage();
	
		try {
			// 解析xml,返回相应的验证信息
			objlist = txu.toXml(requestXml);
			Saopperson emp = (Saopperson) objlist.get(0);
			Saoporg dept = (Saoporg) objlist.get(1);
			System.out.println("getSnameid():" + emp.getSnameid());
			str = drnn.notNull(emp, dept);// 非空验证
			log.info("====报文信息:" + requestXml);
			hm = (HeadManage) objlist.get(2);
			// 验证人员公司是否是本系统维护公司
			/*String validationOgn = empdeptdao.validationOgn(objlist);
			if (validationOgn == "false") {
				rpm.setRtnMsg("合法性错误（’"+emp.getSname()+"‘人员所在的“" + dept.getOrganization() + "”未录入到实物资产系统中）");
				rpm.setRtnCode("40");
				rpm.setDataId(hm.getDataId());
				return txu.resphoneXml(rpm);
			}*/
			// 验证是否存在完全相同数据
			/*String validationPsm = empdeptdao.findSoo(objlist);
			if ("" != validationPsm) {
				rpm.setRtnMsg("合法性错误（" + validationPsm + "）");
				rpm.setRtnCode("40");
				rpm.setDataId(hm.getDataId());
				return txu.resphoneXml(rpm);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			// xml异常
			rpm.setRtnMsg("xml格式错误");
			rpm.setRtnCode("20");
			rpm.setDataId(hm.getDataId());
			return txu.resphoneXml(rpm);

		}
		if (str != "") {// 非空验证
			rpm.setRtnMsg("合法性错误（" + str + "）");
			rpm.setRtnCode("40");
			rpm.setDataId(hm.getDataId());
			return txu.resphoneXml(rpm);
		}
		return empdeptdao.importName(objlist);
	}
}
