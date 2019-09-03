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
import com.entity.HeadManage;
import com.entity.resphoneManage;

import com.entity.Sa_Opperson_Oporg;

public class ToXmlUtilOgnDept {
	Sa_Opperson_Oporg soo;
	List<Object> obj;
	public ToXmlUtilOgnDept(){
		soo=new Sa_Opperson_Oporg();
		obj=new ArrayList<Object>();
	}
	HeadManage hm=new HeadManage();
	SimpleDateFormat sdf = null;
	Date date = new Date();
	
	@SuppressWarnings("unchecked")
	public void getToXml(Element root) throws ParseException {
		
		//System.out.println("当前节点" + root.getName());
		// 遍历节点
		List<Attribute> list = root.attributes();
		// 遍历节点属性
		for (Attribute attribute : list) {
			// System.out.println("属性："+attribute.getName() +":"
			// +attribute.getValue());
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
				soo.setCompanyid(root.getText());
			}
			if (root.getName().equalsIgnoreCase("COMPANY")) {
				soo.setCompanyname(root.getText());
			}
			if (root.getName().equalsIgnoreCase("SORGKINDID")) {
				soo.setSorgkindid(root.getText());
			}
			if (root.getName().equalsIgnoreCase("DEPARTMENTID")) {
				soo.setDepartmentid(root.getText());
			}
			if (root.getName().equalsIgnoreCase("DEPARTMENT")) {
				soo.setDepartmentname(root.getText());
			}
			if (root.getName().equalsIgnoreCase("ORGANIZATIONID")) {
				soo.setSfid(root.getText());
			}
			if (root.getName().equalsIgnoreCase("ORGANIZATION")) {
				soo.setSfname(root.getText());
			}			
			if (root.getName().equalsIgnoreCase("SVALIDSTATE")) {

				soo.setSvalidstate(root.getText());
			}
			if (root.getName().equalsIgnoreCase("SUPERIORDEPARTMENTID")) {

				soo.setSuperiorDepartmentID(root.getText());
			}
			if (root.getName().equalsIgnoreCase("SUPERIORDEPARTMENT")) {
				soo.setSuperiorDepartment(root.getText());
			}
			if (root.getName().equalsIgnoreCase("attr1")) {
				soo.setArrt1(root.getText());
			}
			if (root.getName().equalsIgnoreCase("attr2")) {
				soo.setArrt2(root.getText());
			}
			if (root.getName().equalsIgnoreCase("attr3")) {
				soo.setArrt3(root.getText());
			}
			if (root.getName().equalsIgnoreCase("attr4")) {
				soo.setArrt4(root.getText());
			}
			if (root.getName().equalsIgnoreCase("attr5")) {
				soo.setArrt5(root.getText());
			}
			if (root.getName().equalsIgnoreCase("attr6")) {
				soo.setArrt6(root.getText());
			}
			obj.add(soo);
			obj.add(hm);
			//System.out.println(root.getName() + ":" + root.getText());
		}
		Iterator<Element> iterator = root.elementIterator();
		while (iterator.hasNext()) {
			Element e = iterator.next();
			getToXml(e);
		}

	}

	public List<Object> toXml(String requestXml) throws Exception {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new ByteArrayInputStream(requestXml.getBytes("utf-8")));
		// 遍历节点
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
