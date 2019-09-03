package com.nantian.handler;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class HeaderHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		boolean out = (boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		String ns = "http://soap2.nantian.com/";
		try {
			if(out) {
				SOAPMessage msg = context.getMessage();
				SOAPHeader header = msg.getSOAPHeader();
				QName qname = new QName(ns,"licenseInfo","ns");
				header.addHeaderElement(qname).setValue("lience");;
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
