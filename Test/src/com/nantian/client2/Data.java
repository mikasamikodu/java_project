package com.nantian.client2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.5
 * 2018-08-14T17:59:08.813+08:00
 * Generated source version: 3.2.5
 *
 */
@WebService(targetNamespace = "http://dao.nantian.com/", name = "Data")
@XmlSeeAlso({ObjectFactory.class})
public interface Data {

    @WebMethod
    @RequestWrapper(localName = "getErpStr", targetNamespace = "http://dao.nantian.com/", className = "com.nantian.client2.GetErpStr")
    @ResponseWrapper(localName = "getErpStrResponse", targetNamespace = "http://dao.nantian.com/", className = "com.nantian.client2.GetErpStrResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<java.lang.String> getErpStr(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @RequestWrapper(localName = "delete", targetNamespace = "http://dao.nantian.com/", className = "com.nantian.client2.Delete")
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://dao.nantian.com/", className = "com.nantian.client2.DeleteResponse")
    @WebResult(name = "return", targetNamespace = "")
    public int delete(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @RequestWrapper(localName = "select1", targetNamespace = "http://dao.nantian.com/", className = "com.nantian.client2.Select1")
    @ResponseWrapper(localName = "select1Response", targetNamespace = "http://dao.nantian.com/", className = "com.nantian.client2.Select1Response")
    @WebResult(name = "return", targetNamespace = "")
    public com.nantian.client2.Student select1(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @RequestWrapper(localName = "add", targetNamespace = "http://dao.nantian.com/", className = "com.nantian.client2.Add")
    @ResponseWrapper(localName = "addResponse", targetNamespace = "http://dao.nantian.com/", className = "com.nantian.client2.AddResponse")
    @WebResult(name = "return", targetNamespace = "")
    public int add(
        @WebParam(name = "arg0", targetNamespace = "")
        com.nantian.client2.Student arg0
    );
}