package com.nantian.stax;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Test {

	@org.junit.Test
	public void test1() {
		XMLInputFactory input = XMLInputFactory.newInstance();
		InputStream input1 = null;
		try {
			input1 = Test.class.getClassLoader().getResourceAsStream("book.xml");
			XMLStreamReader reader = input.createXMLStreamReader(input1);
			while(reader.hasNext()) {
				int next = reader.next();
				if (next==XMLStreamConstants.START_ELEMENT) {
					System.out.println(reader.getName());
				}else if(next==XMLStreamConstants.CHARACTERS){
					System.out.println(reader.getText().trim());
				}else if(next==XMLStreamConstants.END_ELEMENT) {
					System.out.println("/"+reader.getName());
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}finally {
			if(input1!=null) {
				try {
					input1.close();
				} catch (IOException e) {
					e.printStackTrace();
				};
			}
		}
	}
	@org.junit.Test
	public void test2() {
		XMLInputFactory input = XMLInputFactory.newInstance();
		InputStream input1 = null;
		try {
			input1 = Test.class.getClassLoader().getResourceAsStream("book.xml");
			XMLStreamReader reader = input.createXMLStreamReader(input1);
			while(reader.hasNext()) {
				int next = reader.next();
				if (next==XMLStreamConstants.START_ELEMENT) {
					String name = reader.getName().toString();
					if("book".equals(name)) {
						System.out.println(reader.getAttributeName(0)+":"+reader.getAttributeValue(0));
					}
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}finally {
			if(input1!=null) {
				try {
					input1.close();
				} catch (IOException e) {
					e.printStackTrace();
				};
			}
		}
	}
	@org.junit.Test
	//基于光标的操作方式
	public void test3() {
		XMLInputFactory input = XMLInputFactory.newInstance();
		InputStream input1 = null;
		try {
			input1 = Test.class.getClassLoader().getResourceAsStream("book.xml");
			XMLStreamReader reader = input.createXMLStreamReader(input1);
			while(reader.hasNext()) {
				int next = reader.next();
				if (next==XMLStreamConstants.START_ELEMENT) {
					String name = reader.getName().toString();
					if("book".equals(name)) {
						System.out.println(reader.getAttributeName(0)+":"+reader.getAttributeValue(0));
					}else if("title".equals(name)) {
						System.out.println(reader.getElementText());
					}else if("price".equals(name)) {
					System.out.println(reader.getElementText()+"\n");
				}
					
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}finally {
			if(input1!=null) {
				try {
					input1.close();
				} catch (IOException e) {
					e.printStackTrace();
				};
			}
		}
	}
	@org.junit.Test
	//基于迭代模型的操作方式
	public void test4() {
		XMLInputFactory input = XMLInputFactory.newInstance();
		InputStream input1 = null;
		try {
			input1 = Test.class.getClassLoader().getResourceAsStream("book.xml");
			XMLEventReader reader = input.createXMLEventReader(input1);
			int num = 0;
			while(reader.hasNext()) {
				XMLEvent next = reader.nextEvent();
				if (next.isStartElement()) {
					String name = next.asStartElement().getName().toString();
					if("title".equals(name)) {
						System.out.println(reader.getElementText());
					}else if("price".equals(name)) {
						System.out.println(reader.getElementText()+"\n");
					}
					
				}
				num++;
			}
			System.out.println(num);
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}finally {
			if(input1!=null) {
				try {
					input1.close();
				} catch (IOException e) {
					e.printStackTrace();
				};
			}
		}
	}
	@org.junit.Test
	//基于迭代模型的操作方式
	public void test5() {
		XMLInputFactory input = XMLInputFactory.newInstance();
		InputStream input1 = null;
		try {
			input1 = Test.class.getClassLoader().getResourceAsStream("book.xml");
			XMLEventReader reader = input.createFilteredReader(input.createXMLEventReader(input1), new EventFilter() {
				
				@Override
				public boolean accept(XMLEvent event) {
					if(event.isStartElement()) {
						String name = event.asStartElement().getName().toString();
						if("title".equals(name)||"price".equals(name)) {
							return true;
						}
					}
					return false;
				}
			});
			int num = 0;
			while(reader.hasNext()) {
				XMLEvent next = reader.nextEvent();
				if (next.isStartElement()) {
					String name = next.asStartElement().getName().toString();
					if("title".equals(name)) {
						System.out.println(reader.getElementText());
					}else if("price".equals(name)) {
						System.out.println(reader.getElementText()+"\n");
					}
					
				}
				num++;
			}
			System.out.println(num);
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}finally {
			if(input1!=null) {
				try {
					input1.close();
				} catch (IOException e) {
					e.printStackTrace();
				};
			}
		}
	}
	@org.junit.Test
	//基于迭代模型的操作方式
	public void test6() {
		InputStream input1 = null;
		try {
			input1 = Test.class.getClassLoader().getResourceAsStream("book.xml");
			//创建文档处理对象
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			//通过DocumentBuilder创建document的文档对象
			Document document = builder.parse(input1);
			//创建xpath
			XPath path = XPathFactory.newInstance().newXPath();
			//第一个参数是xpath，第二个是文档对象
			NodeList node = (NodeList)path.evaluate("//book[@category='WEB']", document, XPathConstants.NODESET);
			for(int i=0;i<node.getLength();i++) {
				//遍历输出结果
				Element item = (Element)node.item(i);
				System.out.println(item.getElementsByTagName("title").item(0).getTextContent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(input1!=null) {
				try {
					input1.close();
				} catch (IOException e) {
					e.printStackTrace();
				};
			}
		}
	}
	
	@org.junit.Test
	public void test7() {
		XMLStreamWriter writer;
		try {
			writer = XMLOutputFactory.newInstance().createXMLStreamWriter(System.out);
			writer.writeStartDocument("GBK", "1.0");
			writer.writeEndDocument();
			writer.writeStartElement("person");
			writer.writeStartElement("id");
			writer.writeCharacters("1");
			writer.writeEndElement();
			writer.writeEndElement();
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	@org.junit.Test
	public void test08() {
		InputStream is = null;
		try {
			is = Test.class.getClassLoader().getResourceAsStream("book.xml");
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = builder.parse(is);
			XPath path = XPathFactory.newInstance().newXPath();
			Transformer trans = TransformerFactory.newInstance().newTransformer();
			trans.setOutputProperty(OutputKeys.ENCODING, "GBK");
			NodeList list = (NodeList)path.evaluate("//book[@title='Learning XML']", document, XPathConstants.NODESET);
			Element element1 = (Element)(list.item(0));
			Element element2 = (Element)(element1.getElementsByTagName("price").item(0));
			element2.setTextContent("2233");
			Result result = new StreamResult(System.out);
			trans.transform(new DOMSource(document), result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				};
			}
		}
		
	}
	
	
}
