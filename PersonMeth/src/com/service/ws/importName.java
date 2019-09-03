package com.service.ws;

import javax.jws.WebService;

@WebService
public interface importName {
	String importNameInfo(String requestXml);
}
