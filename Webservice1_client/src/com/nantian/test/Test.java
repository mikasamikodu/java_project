package com.nantian.test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.Date;
//import java.util.List;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.util.BaseDao;

public class Test {

	public static void main(String[] args) {
		BaseDao bd=null;
		Connection conn = null;
		PreparedStatement pstm= null;
		ResultSet rs = null;
		long i=100;
		

		String info="";
//		WebServiceImplService webServiceImplService=new WebServiceImplService();
//		WebServiceImpl webServiceImpl=webServiceImplService.getPort(WebServiceImpl.class);
	
//		List<String> returnList = new ArrayList<String>();
		
		String sql = "select t.fCode as fCode,t.fName as fName,"
				+ "t.fkindid as fkindid,t.fResponseOgnID as fResponseOgnID,t.fFactory as fFactory,"
				+ "t.fCreateTime as fCreateTime,t.fOriginValue as fOriginValue,t.fDutyDeptID as fDutyDeptID,"
				//+ "t.fBuyDate as fBuyDate,t.fOriginValue as fOriginValue,t.fDutyDeptID as fDutyDeptID,"
				+ "t.fStatusName as fStatusName" + " from oa_as_card t"
		//		+ " where t.FIMPORTFADATE>=to_date('" + time + "','yyyy-mm-dd hh24:mi:ss') and t.fErpStatus = '1'";
				+ " where t.fErpStatus = '1'";	
		try {
			bd=new BaseDao();
			conn=bd.getConnection();
			pstm = conn.prepareStatement(sql);
//			System.out.println("执行的sql语句是:" + sql);
			rs = pstm.executeQuery();
			System.out.println(sql);
			while (rs.next()) {
				// 数据id
			    String flag_data_id = rs.getString("fCode");
//			    String flag2_data_id = flag_data_id.substring(flag_data_id.length()).trim();
			    String flag2_data_id = flag_data_id.substring(6,flag_data_id.length()).trim();
			    Long req_data_id = Long.valueOf(flag2_data_id);
				//请求的批次
//			    Long req_batch_id = req_data_id;
				// 组织编码
			    //赵九宝修改
			    String org_code = rs.getString("fResponseOgnID");
				if (org_code == null || "".equals(org_code)) {
					org_code = "<org_code></org_code>";
				} else if ("81".equals(org_code)) {
					org_code = "<org_code>20000002</org_code>";  // 国家开发投资公司
				} else if ("85".equals(org_code)) {
					org_code = "<org_code>20000364</org_code>";  // 国投物业有限责任公司
				} else if ("86".equals(org_code)) {
					org_code = "<org_code>20000365</org_code>";  // 国投物业有限责任公司-一分公司
				} else if ("87".equals(org_code)) {
					org_code = "<org_code>20000366</org_code>";  // 国投物业有限责任公司-二分公司
				} else if ("88".equals(org_code)) {
					org_code = "<org_code>20000201</org_code>";  // 国投交通公司
				} else if ("90".equals(org_code)) {
					org_code = "<org_code>20000646</org_code>";  // 国投资产管理公司
				} else if ("92".equals(org_code)) {
					org_code = "<org_code>20000645</org_code>";  // 中投咨询有限公司
				} else if ("2004".equals(org_code)) {
					org_code = "<org_code>20000414</org_code>";  // 国投安信股份有限公司
				} else if ("254".equals(org_code)) {
					org_code = "<org_code>20000267</org_code>";  // 国投高科技投资有限公司
				} else if ("534".equals(org_code)) {
					org_code = "<org_code>20000361</org_code>";  // 国投财务有限公司
				} else if ("714".equals(org_code)) {
					org_code = "<org_code>20000320</org_code>";  // 国投资本控股有限公司
				} else if ("840".equals(org_code)) {
					org_code = "<org_code>20000367</org_code>";  // 国投物业有限责任公司-三分公司
				} else if ("920".equals(org_code)) {
					org_code = "<org_code>20000631</org_code>";  // 融实国际控股有限公司
				} else if ("1041".equals(org_code)) {
					org_code = "<org_code>20000368</org_code>";  // 国投物业有限责任公司-上海分公司
				} else if ("1144".equals(org_code)) {
					org_code = "<org_code>20000362</org_code>";  // 国投财务有限公司-国投保险经济有限公司
				} else if ("1164".equals(org_code)) {
					org_code = "<org_code>20000369</org_code>";  // 国投物业有限责任公司-五分公司
				} else if ("1222".equals(org_code)) {
					org_code = "<org_code>20000632</org_code>";  // 国投融资租赁有限公司
				} else if ("1243".equals(org_code)) {
					org_code = "<org_code>20000208</org_code>";   // 国投交通控股有限公司
				} else if ("1283".equals(org_code)) {
					org_code = "<org_code>20000649</org_code>";  // 国投创益产业基金管理有限公司
				} else if ("1452".equals(org_code)) {
					org_code = "<org_code>20000590</org_code>";  // 中成国际糖业股份有限公司
				} else if ("1453".equals(org_code)) {
					org_code = "<org_code>20000568</org_code>";  // 中成进出口股份有限公司
				} else if ("1641".equals(org_code)) {
					org_code = "<org_code>20000607</org_code>";  // 中非技术贸易有限公司
				} else if ("839".equals(org_code)) {
					org_code = "<org_code>20000622</org_code>";  // 中成集团总公司
				} else if ("1893".equals(org_code)) {
					org_code = "<org_code>20000654</org_code>";  // 国投海外投资有限公司
				} else if ("001".equals(org_code)) {
					org_code = "<org_code>20000005</org_code>";  // 国投电力有限公司
				} else if ("2414".equals(org_code)) {
					org_code = "<org_code>20001672</org_code>";  // 国投健康产业投资有限公司
				} else if ("2420".equals(org_code)) {
					org_code = "<org_code>20000856</org_code>";  // 国投颐康（北京）养老投资有限公司
				} else if ("2329".equals(org_code)) {
					org_code = "<org_code>20000650</org_code>";  // 国投惠康投资有限公司
				} else if ("2419".equals(org_code)) {
					org_code = "<org_code>20000855</org_code>";  // 广州国投悦康养老服务有限公司
				} else if ("2041".equals(org_code)) {
					org_code = "<org_code>20002041</org_code>";  // 国投生物科技投资有限公司
				} else if ("414".equals(org_code)) {
					org_code = "<org_code>20000250</org_code>";  // 国投物流投资有限公司
				} else if ("1669".equals(org_code)) {
					org_code = "<org_code>20001669</org_code>";  // 国投智能科技有限公司
				} else if ("1864".equals(org_code)) {
					org_code = "<org_code>20001864</org_code>";  // 国投生物微藻公司
				}else {
					org_code = "<org_code>"+ org_code +"</org_code>";
				}
				
				// 资产名称
				String asset_name = rs.getString("fName");
				if (asset_name == null || "".equals(asset_name)) {
					asset_name = "<asset_name></asset_name>";
				} else {
					asset_name = "<asset_name>" + asset_name + "</asset_name>";
				}
				// 资产标签号,erp系统接收最大长度为15位,超过15位截取字符串
				String tag_number = rs.getString("fCode");
				//String tag_number1 = rs.getString("fCode").substring(5);
				if (tag_number == null || "".equals(tag_number) || tag_number.length() > 15) {
					//tag_number = "<tag_number>"+ tag_number1+"</tag_number>";
					tag_number = "<tag_number></tag_number>";
				} else {
					tag_number = "<tag_number>" + tag_number + "</tag_number>";
				}
				// 资产类别ID
				String category_id = rs.getString("fKindID");
				String flag2 = category_id.substring(0,2);
				String flag3 = category_id.substring(0,3);
				if (category_id == null || "".equals(category_id)) {
					category_id = "<category_id></category_id>";
				} else if ("10".equals(flag2)) {
					category_id = "<category_id>7033</category_id>"; // 房屋及建筑物
				} else if ("201".equals(flag3) || "202".equals(flag3) || "21".equals(flag2) || "22".equals(flag2) || "23".equals(flag2) || "30".equals(flag2)) {
					if ("2010901".equals(category_id) || "2010902".equals(category_id) || "2010903".equals(category_id) || "2010999".equals(category_id) || "2019900".equals(category_id)) {
						category_id = "<category_id>7040</category_id>"; // 软件
					} else if ("2101009".equals(category_id)) {
						category_id = "<category_id>7033</category_id>"; // 房屋及建筑物
					} else if ("2200911".equals(category_id) || "2200914".equals(category_id) || "2201103".equals(category_id) || "2201104".equals(category_id) || "2201105".equals(category_id) || "2201199".equals(category_id)) {
						category_id = "<category_id>7031</category_id>"; // 非电子类办公设备
					} else {
					category_id = "<category_id>7030</category_id>"; // 电子类办公设备
					}
				} else if ("203".equals(flag3)) {
					category_id = "<category_id>7032</category_id>"; // 运输工具
				} else if ("204".equals(flag3) || "320".equals(flag3) || "31".equals(flag2)) {
					category_id = "<category_id>7031</category_id>"; // 非电子类办公设备
				} else if ("70".equals(flag2) || "50".equals(flag2) || "40".equals(flag2) || "37".equals(flag2) || "322".equals(flag3) || "324".equals(flag3)) {
					category_id = "<category_id>7036</category_id>"; // 其他固定资产
				} else if ("60".equals(flag2)) {
					category_id = "<category_id>7035</category_id>"; // 酒店及家具
				} else if ("24".equals(flag2)) {
					category_id = "<category_id>7034</category_id>"; // 机器设备
				} else {
					category_id = "<category_id></category_id>";
				}
				// 型号
				String model_number = "<model_number></model_number>";
				// 制造商
				String manufacturer_name = rs.getString("fFactory");
				if (manufacturer_name == null || "".equals(manufacturer_name)) {
					manufacturer_name = "<manufacturer_name></manufacturer_name>";
				} else {
					manufacturer_name = "<manufacturer_name>" + manufacturer_name + "</manufacturer_name>";
				}
				// 序列号
				String serial_number = rs.getString("fCode");
				if (serial_number == null || "".equals(serial_number)) {
					serial_number = "<serial_number></serial_number>";
				} else {
					serial_number = "<serial_number>" + serial_number + "</serial_number>";
				}
				// Date-->String 启用日期
				Timestamp fCreateTime = rs.getTimestamp("fCreateTime");
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				String date_placed_in_service = "";
				if (fCreateTime == null) {
					date_placed_in_service = sf.format(new Date());
				} else {
					date_placed_in_service = sf.format(fCreateTime);
				}
				if (date_placed_in_service == null || "".equals(date_placed_in_service)) {
					date_placed_in_service = "<date_placed_in_service></date_placed_in_service>";
				} else {
					date_placed_in_service = "<date_placed_in_service>" + date_placed_in_service
							+ "</date_placed_in_service>";
				}
				// 原值
				BigDecimal fOriginValue = rs.getBigDecimal("fOriginValue");
				double assert_cost = fOriginValue.doubleValue(); 
				// 数量
				int fixed_assets_units = 1;
				// 费用科目编码
				String acct_code = "";
				String fKindID = rs.getString("fKindID");
				String flag = fKindID.substring(0, 5);
				if (fKindID == null || "".equals(fKindID)) {
					acct_code = "<acct_code></acct_code>";
				} else {
					if ("20109".equals(flag)) {
						acct_code = "<acct_code>6602040</acct_code>";
					} else {
						acct_code = "<acct_code>6602026</acct_code>";
					}
				}
				// 部门编码
				String dept_code = "<dept_code>0</dept_code>";
				// 状态
				String location_status = "<location_status>在用</location_status>";
				// 是否折旧
				String depreciate_flag = "<depreciate_flag>YES</depreciate_flag>";
				// 摊销调整
				String amortize_flag = "<amortize_flag>NO</amortize_flag>";
				// 资产类型
				String asset_type = "<asset_type>CAPITALIZED</asset_type>";
				// 是否在库存
				String inventorial = "<inventorial>YES</inventorial>";
				StringBuffer sb = new StringBuffer();
				// sb.append("<?xml version='1.0' encoding='UTF-8'?>");
				sb.append("<config>" + "<req_system_code>SWZCXT</req_system_code>"
						+ "<req_user_name>swzcxt</req_user_name>"
						+ "<req_user_password>f5bb3d62ace2a648751bc0cc83357eb5</req_user_password>");
				String is_import = ("<is_import>N</is_import>");
				i=i+1;
				String n=new SimpleDateFormat("yyMMdd").format(new Date())+i;
				sb.append("<req_batch_id>" + n + "</req_batch_id>").append("<req_data_id>" + req_data_id + "</req_data_id>").append(org_code).append(is_import);
//				System.out.println(n);
				sb.append("<asset_info>");
				sb.append(asset_name).append(tag_number);
				sb.append(category_id).append(model_number);
				sb.append(manufacturer_name).append(serial_number).append(date_placed_in_service);
				sb.append("<assert_cost>" + assert_cost + "</assert_cost>").append("<fixed_assets_units>" + fixed_assets_units + "</fixed_assets_units>").append(acct_code).append(dept_code);
				sb.append(location_status).append(depreciate_flag).append(amortize_flag);
				sb.append(asset_type).append(inventorial);
				sb.append("</asset_info></config>\r\n");
				info=sb.toString();
//				System.out.println(info+"\n");
				//returnList.add(sb.toString());
				String wsdlURL = "http://erptest2.sdic.com.cn:8080/GtjtIntfService/service/WsExampleService?wsdl";

				 JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			     Object[] objects = dcf.createClient(wsdlURL).invoke( "importFa", info);
			     for(int a=0;a<objects.length;a++) {
			    	 System.out.println(objects[a]);
			     }
				/*String ns = "http://service.interf.gtjt.cux/";
				String wsdlURL = "http://erptest2.sdic.com.cn:8080/GtjtIntfService/service/WsExampleService?wsdl";
				String wsdlURL = "http://erptest2.sdic.com.cn:8080/GtjtIntfService/service/WsExampleService?wsdl";
				String wsdlURL = "http://erptest2.sdic.com.cn:8080/GtjtIntfService/service/WsExampleService?wsdl";
				String wsdlURL = "http://erptest2.sdic.com.cn:8080/GtjtIntfService/service/WsExampleService?wsdl";
				URL url = new URL(wsdlURL);
				QName qname = new QName(ns,"GtjtserviceService");
//				Igtjtservice nn = GtjtserviceService.create( url, qname).getPort(Igtjtservice.class);
				Service service = GtjtserviceService.create( url, qname);
//				-<wsdl:port name="GtjtservicePort
				
				Dispatch<Source> dispatch = service.createDispatch(new QName(ns,"GtjtservicePort"),Source.class,Service.Mode.PAYLOAD);
				StreamSource source = new StreamSource(new StringReader(info));
				//发送请求报文并接收响应报文
				Source responce = dispatch.invoke(source);
				//将响应报文通过Transformer转换成document结构
				DOMResult result = new DOMResult();
				Transformer trans = TransformerFactory.newInstance().newTransformer();
				trans.transform(responce, result);
				//通过XPath解析响应报文
				XPath path = XPathFactory.newInstance().newXPath();
				NodeList list = (NodeList)path.evaluate("//user",result.getNode(),XPathConstants.NODESET);
				System.out.println(list.item(0).getNodeName());
//				String mm = nn.importFa(info);
//				System.out.println(mm);
				
				
//				-<wsdl:service name="GtjtserviceService">



//-<wsdl:service name="GtjtserviceService">
//-<wsdl:port name="GtjtservicePort" binding="tns:GtjtserviceServiceSoapBinding">
				URL url1 = new URL(wsdlURL);
				QName qname1 = new QName(ns,"GtjtserviceService");
				Service service1 = Service.create(url,qname);
				//创建Dispatch，通过SOAPMessage传递消息
				Dispatch<SOAPMessage> dispatch1 = service.createDispatch(new QName(ns,"GtjtserviceServiceSoapBinding"),SOAPMessage.class,Service.Mode.MESSAGE);
				//通过QName设置SOAPMessage的Body
				SOAPMessage message = MessageFactory.newInstance().createMessage();*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Statement stmt = null;
//		try {
//			conn = ModelUtils.getConnection("/OA");
//			stmt = conn.createStatement();
//			System.out.println("测试程序");
//			String sql33 = "select ferpstatus from oa_as_card where ferpstatus = '1'";
//			ps = conn.prepareStatement(sql33);
//			rs = ps.executeQuery();
//			if(!rs.next()){
//				System.out.println("无数据发送给ERP系统");
//			}else{
//				
//				String updateERP = "update oa_as_card a" + " set ferpstatus = '2' where a.ferpstatus = '1'";
//				stmt.executeUpdate(updateERP);
//			}			
//			System.out.println("程序测试结束!!!");
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (stmt != null) {
//				stmt.close();
//				stmt = null;
//			}
//			if (conn != null) {
//				conn.close();
//				conn = null;
//			}
//		}
		
//		return returnList;

		
		System.out.println("打印结束");
	}

}
