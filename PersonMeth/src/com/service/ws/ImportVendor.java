package com.service.ws;

import javax.jws.WebService;

@WebService
public interface ImportVendor {
	String importVendorInfo(String requestXml);
}
