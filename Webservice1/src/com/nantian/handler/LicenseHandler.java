package com.nantian.handler;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LicenseHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		boolean out = (boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		try {
			if(!out) {
				SOAPMessage msg = context.getMessage();
				SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
				SOAPHeader header = envelope.getHeader();
				SOAPBody body = envelope.getBody();
				Node node = body.getChildNodes().item(0);
				String part = node.getLocalName();
				if("addUser".equals(part)||"list".equals(part)) {
					if(header!=null) {
						NodeList list = header.getElementsByTagName("ns:licenseInfo");
						String head = list.item(0).getTextContent();
						System.out.println(head);
					} else if(header==null||!header.extractAllHeaderElements().hasNext()){
						SOAPFault fault = body.addFault();
						fault.setFaultString("头部信息不能为空");
						throw new SOAPFaultException(fault);
					}
				}
			}
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return false;
	}

	@Override
	public void close(MessageContext context) {
		
	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}

}
