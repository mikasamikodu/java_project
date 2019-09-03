package com.nantian.test;

import java.io.*;
import java.net.*;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.*;
import javax.xml.ws.soap.SOAPFaultException;
import javax.xml.xpath.*;

import org.w3c.dom.*;
import org.w3c.dom.Node;

import com.nantian.soap2.User;

public class Test {

	private String wsdlURL = "http://erptest2.sdic.com.cn:8080/GtjtIntfService/service/WsExampleService";
//	private String wsdlURL = "http://localhost:9999/server";
//	targetNamespace="http://service.interf.gtjt.cux/
	private String ns = "http://service.interf.gtjt.cux/";
//	private String ns = "http://soap2.nantian.com/";
	
	@org.junit.Test
	public void test01() {
		try {//先创建消息工厂
			MessageFactory mess = MessageFactory.newInstance();
			//再创建消息
			SOAPMessage messager = mess.createMessage();
			//得到soap部分
			SOAPPart part = messager.getSOAPPart();
			//得到soap envelope
			SOAPEnvelope envelope = part.getEnvelope();
			//得到soap主体
			SOAPBody body = envelope.getBody();
			//根据qname创建相应节点
			QName name = new QName("http://java.nantian.edu.cn/webservice", "add","ns");
			SOAPBodyElement element = body.addBodyElement(name);
			element.addChildElement("a").setValue("22");
			element.addChildElement("b").setValue("33");
			//打印消息
			messager.writeTo(System.out);
			
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@org.junit.Test
	public void test02() {
		try {
			URL url = new URL(wsdlURL);
			QName qName = new QName(ns, "MyServiceImplService");
			Service service = Service.create(url, qName);
			
			Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(ns,"MyServiceImplPort"), SOAPMessage.class, Service.Mode.MESSAGE);
			
			SOAPMessage msg = MessageFactory.newInstance().createMessage();
			SOAPPart part = msg.getSOAPPart();
			SOAPEnvelope envelope = part.getEnvelope();

			SOAPBody body = envelope.getBody();
			QName qname = new QName(ns, "login","ns");
			SOAPBodyElement element = body.addBodyElement(qname);
//			element.addChildElement("username").setValue("张三");
			element.addChildElement("username").setValue("zhangsan");
			element.addChildElement("password").setValue("1234");
			msg.writeTo(System.out);
			System.out.println();
			
			SOAPMessage invoke = dispatch.invoke(msg);
			invoke.writeTo(System.out);
			System.out.println();
//			Document document = invoke.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
//			String result = document.getElementsByTagName("user").item(0).getTextContent();
//			System.out.println(result);
		} catch(SOAPFaultException e) {
			System.out.println(e.getMessage());
		} catch(SOAPException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@org.junit.Test
	public void test03() {
		try {
			//创建服务
			URL url = new URL(wsdlURL);
			QName qName = new QName(ns, "MyServiceImplService");
			Service service = Service.create(url, qName);
			//创建dispatch，通过源数据的方式进行传递
			Dispatch<Source> dispatch = service.createDispatch(new QName(ns,"MyServiceImplPort"), Source.class, Service.Mode.PAYLOAD);
			//根据用户对象创建xml
			User user = new User("3","tom","jack","1234");
			JAXBContext jax = JAXBContext.newInstance(User.class);
			Marshaller masl = jax.createMarshaller();
			masl.setProperty(Marshaller.JAXB_FRAGMENT, true);
			StringWriter writer = new StringWriter();
			masl.marshal(user, writer);
			
			//封装相应的part,addUser
			String payload = "<ns:addUser xmlns:ns=\""+ns+"\">"+writer.toString()+"</ns:addUser>";
			System.out.println(payload);
			StreamSource rs = new StreamSource(new StringReader(payload));
			
			//用dispatch传递payload
			Source responce = dispatch.invoke(rs);
			
			//将Source转换为DOM进行操作，使用Transformer转换
			Transformer trans = TransformerFactory.newInstance().newTransformer();
			DOMResult result = new DOMResult();
			trans.transform(responce, result);
			
			//通过XPath处理响应消息
			XPath path = XPathFactory.newInstance().newXPath();
			NodeList list = (NodeList)path.evaluate("//user", result.getNode(), XPathConstants.NODESET);
			System.out.println(list.item(0).getNodeName());;
			User  user1 = (User)jax.createUnmarshaller().unmarshal(list.item(0));
			System.out.println(user1.getNickname());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@org.junit.Test
	public void test04() {
		try {
			//创建服务service
			URL url = new URL(wsdlURL);
			QName qname = new QName(ns,"MyServiceImplService");
			Service service = Service.create(url,qname);
			//创建dispatch通过source源数据的方式传递数据
			Dispatch<Source> dispatch = service.createDispatch(new QName(ns,"MyServiceImplPort"), Source.class, Service.Mode.PAYLOAD);
			//根据用户对象创建xml
			User user = new User("4","rose","rose1","1234");
			//通过JAXBContext编译对象
			JAXBContext jaxb = JAXBContext.newInstance(User.class);
			Marshaller marshaller = jaxb.createMarshaller();
			StringWriter writer = new StringWriter();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			//将对象进行编译
			marshaller.marshal(user, writer);
			//制作xml
			String payload = "<ns:addUser xmlns:ns=\""+ns+"\">"+writer.toString()+"</ns:addUser>";
			System.out.println(payload);
			
			StreamSource source = new StreamSource(new StringReader(payload));
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
			//通过JAXBContext对响应报文中的对象进行反编译，转换成对象
			User user1 = (User)jaxb.createUnmarshaller().unmarshal(list.item(0));
			System.out.println(user1.getUsername());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch(JAXBException e) {
			e.printStackTrace();
		} catch(TransformerConfigurationException e) {
			e.printStackTrace();
		} catch(TransformerException e) {
			e.printStackTrace();
		} catch(XPathExpressionException e) {
			e.printStackTrace();
		}
	}
	
	@org.junit.Test
	public void test05() {
		try {
			//创建Service
			URL url = new URL(wsdlURL);
			QName qname = new QName(ns,"MyServiceImplService");
			Service service = Service.create(url,qname);
			//创建Dispatch，通过SOAPMessage传递消息
			Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(ns,"MyServiceImplPort"),SOAPMessage.class,Service.Mode.MESSAGE);
			//通过QName设置SOAPMessage的Body
			SOAPMessage message = MessageFactory.newInstance().createMessage();
			SOAPPart part = message.getSOAPPart();
			SOAPEnvelope envelope = part.getEnvelope();
			
			SOAPHeader header = envelope.getHeader();
			if(header == null) header = envelope.addHeader();
			header.addHeaderElement(new QName(ns,"authInfo","ns")).setValue("rise");;
			
			SOAPBody body = envelope.getBody();
			body.addBodyElement(new QName(ns,"list","ns"));
			message.writeTo(System.out);
			System.out.println();
			//通过dispatch传递请求报文，获取响应报文
			SOAPMessage response = dispatch.invoke(message);
			response.writeTo(System.out);
			System.out.println();
			//将响应报文转换为Document结构
			Document dom = response.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
			//由于得到的是list所以直接获取所有的相关节点
			NodeList list = (NodeList)dom.getElementsByTagName("user");
			//通过JAXBContext进行反编译
			JAXBContext jaxb = JAXBContext.newInstance(User.class);
			for(int a=0;a<list.getLength();a++) {
				Node node = list.item(a);
				//通过反编译获取对象
				User user = (User)jaxb.createUnmarshaller().unmarshal(node);
				System.out.println(user.getUsername());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@org.junit.Test
	public void test06() {
		try {
			//GtjtserviceService
			URL url = new URL(wsdlURL);
			QName qName = new QName(ns, "GtjtserviceService");
			Service service = Service.create(url, qName);

			Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(ns,"GtjtserviceServiceSoapBinding"), SOAPMessage.class, Service.Mode.MESSAGE);
			
			SOAPMessage msg = MessageFactory.newInstance().createMessage();
			SOAPPart part = msg.getSOAPPart();
			SOAPEnvelope envelope = part.getEnvelope();

			SOAPBody body = envelope.getBody();
			QName qname = new QName(ns, "importFa","tns");
			SOAPBodyElement element = body.addBodyElement(qname);
//			element.addChildElement("username").setValue("张三");
			element.addChildElement("username").setValue("zhangsan");
			element.addChildElement("password").setValue("1234");
			msg.writeTo(System.out);
			System.out.println();
			
			SOAPMessage invoke = dispatch.invoke(msg);
			invoke.writeTo(System.out);
			System.out.println();
//			Document document = invoke.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
//			String result = document.getElementsByTagName("user").item(0).getTextContent();
//			System.out.println(result);
		} catch(SOAPFaultException e) {
			System.out.println(e.getMessage());
		} catch(SOAPException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
