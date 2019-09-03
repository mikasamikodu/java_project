package com.service.ws;

import javax.jws.WebService;

@WebService
public interface importCorp {
	String importCorpInfo(String requestXml);
}
