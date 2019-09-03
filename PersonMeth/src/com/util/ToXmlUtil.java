package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.fusesource.hawtbuf.ByteArrayInputStream;

import com.entity.Saoporg;
import com.entity.Saopperson;
import com.entity.HeadManage;
import com.entity.resphoneManage;

public class ToXmlUtil {
	private Saoporg dept = new Saoporg();
	private Saopperson emp = new Saopperson();
	private HeadManage hm=new HeadManage();
	SimpleDateFormat sdf = null;
	Date date = new Date();
	List<Object> obj = new ArrayList<Object>();

	@SuppressWarnings("unchecked")
	public void getToXml(Element root) throws ParseException {
		// 遍历节点
		List<Attribute> list = root.attributes();
		//遍历节点属性
		for (Attribute attribute : list) {
			// System.out.println("属性："+attribute.getName() +":" +attribute.getValue());
			if (attribute.getName().equalsIgnoreCase("systemname")) {
				hm.setSystemName(attribute.getValue());
			}
			if (attribute.getName().equalsIgnoreCase("billdefine")) {
				hm.setBillDefine(attribute.getValue());
			}
			if (attribute.getName().equalsIgnoreCase("userid")) {
				hm.setUserId(attribute.getValue());
			}
			if (attribute.getName().equalsIgnoreCase("operatertype")) {
				hm.setOperaterType(attribute.getValue());
			}
			if (attribute.getName().equalsIgnoreCase("dataid")) {
				hm.setDataId(attribute.getValue());
			}
		}
		// 如果节点有值，获取值
		// System.out.println(root.getName() + ":" + root.getText());
		if (!(root.getTextTrim().equals(""))) {
			if (root.getName().equalsIgnoreCase("COMPANYID")) {
				dept.setCompanyid(root.getText());
			}
			if (root.getName().equalsIgnoreCase("COMPANY")) {
				dept.setCompany(root.getText());
			}
			
			if (root.getName().equalsIgnoreCase("SORGKINDID")) {
				dept.setSorgKindId(root.getText());
			}
			if (root.getName().equalsIgnoreCase("SNAMEID")) {
				emp.setSnameid(root.getText());
			}
			if (root.getName().equalsIgnoreCase("SID")) {
				String sid=root.getText();					
				emp.setSid(sid);		
			}
			if (root.getName().equalsIgnoreCase("ORGANIZATIONID")) {
				dept.setOrganizationID(root.getText());
			}
			if (root.getName().equalsIgnoreCase("ORGANIZATION")) {
				dept.setOrganization(root.getText());
			}
			if (root.getName().equalsIgnoreCase("DEPARTMENTID")) {
				//dept.setSid(root.getText());
				dept.setDepartmentID(root.getText());
			}
			if (root.getName().equalsIgnoreCase("DEPARTMENT")) {
				dept.setDepartment(root.getText());
			}			
			// emp
			if(root.getName().equalsIgnoreCase("NAMECODE")){
				emp.setNameCode(root.getText());
			}
			if(root.getName().equalsIgnoreCase("SSTATE")){
				emp.setSstate(root.getText());
			}
			if(root.getName().equalsIgnoreCase("SSEQUENCE")){
				emp.setSsequence(root.getText());
			}
			if(root.getName().equalsIgnoreCase("SNUMB")){
				emp.setSnubm(root.getText());
			}
			if (root.getName().equalsIgnoreCase("NAME")) {
				emp.setSname(root.getText());
			}
			if (root.getName().equalsIgnoreCase("SEX")) {
				emp.setSex(root.getText());
			}
			if (root.getName().equalsIgnoreCase("IDCARD")) {
				emp.setSidCard(root.getText());
			}
			if (root.getName().equalsIgnoreCase("SOFFICEPHONE")) {
				String sofficePhone=root.getText();
				if("null".equals(sofficePhone)){
					sofficePhone="";
				}
				emp.setSofficePhone(sofficePhone);
			}
			if (root.getName().equalsIgnoreCase("SMOBILEPHONE")) {
				String smobilePhone=root.getText();
				if("null".equals(smobilePhone)){
					smobilePhone="";
				}
				emp.setSmobilePhone(smobilePhone);
			}
			if (root.getName().equalsIgnoreCase("JOINDATE")) {

				emp.setJoinDate(root.getText());
			}
			if (root.getName().equalsIgnoreCase("SVALIDSTATE")) {

				emp.setSvalIdState(root.getText());
			}
			if (root.getName().equalsIgnoreCase("BIRTHDAY")) {
				emp.setBirthDay(root.getText());
			}
			
			if (root.getName().equalsIgnoreCase("attr1")) {
				emp.setArrt1(root.getText());
			}
			if (root.getName().equalsIgnoreCase("attr2")) {
				emp.setArrt2(root.getText());
			}
			if (root.getName().equalsIgnoreCase("attr3")) {
				emp.setArrt3(root.getText());
			}
			if (root.getName().equalsIgnoreCase("attr4")) {
				emp.setArrt4(root.getText());
			}
			if (root.getName().equalsIgnoreCase("attr5")) {
				emp.setArrt5(root.getText());
			}
			if (root.getName().equalsIgnoreCase("attr6")) {
				emp.setArrt6(root.getText());
			}
			obj.add(emp);
			obj.add(dept);
			obj.add(hm);
		}
		Iterator<Element> iterator = root.elementIterator();
		while (iterator.hasNext()) {
			Element e = iterator.next();
			getToXml(e);
		}

	}

	public List<Object> toXml(String requestXml) throws Exception {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new ByteArrayInputStream(requestXml
				.getBytes("utf-8")));
		//遍历节点
		Element root = document.getRootElement();
		getToXml(root);
		return obj;
	}

	/**
	 * 
	 * @param 传入操作结果参数
	 * @return 返回相应参数
	 */
	public String resphoneXml(resphoneManage rpm) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>\n");
		sb.append("<returninfo>\n");
		sb.append("\t<dataID>" + rpm.getDataId() + "<dataID>\n");
		sb.append("\t<rtnCode>" + rpm.getRtnCode() + "</rtnCode>\n");
		sb.append("\t<rtnMsg>" + rpm.getRtnMsg() + "</rtnMsg>\n");
		sb.append("</returninfo>");
		return sb.toString();
	}
}
