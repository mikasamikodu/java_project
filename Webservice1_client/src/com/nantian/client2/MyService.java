
package com.nantian.client2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MyService", targetNamespace = "http://www.example.org/mywsdl/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MyService {


    /**
     * 
     * @param a
     * @param b
     * @return
     *     returns int
     */
    @WebMethod(action = "http://www.example.org/mywsdl/add")
    @WebResult(name = "addResult", targetNamespace = "")
    @RequestWrapper(localName = "add", targetNamespace = "http://www.example.org/mywsdl/", className = "com.nantian.client2.AddType")
    @ResponseWrapper(localName = "addResponse", targetNamespace = "http://www.example.org/mywsdl/", className = "com.nantian.client2.AddResponse")
    public int add(
        @WebParam(name = "a", targetNamespace = "")
        int a,
        @WebParam(name = "b", targetNamespace = "")
        int b);

    /**
     * 
     * @param num1
     * @param num2
     * @return
     *     returns int
     */
    @WebMethod(action = "http://www.example.org/mywsdl/divede")
    @WebResult(name = "divedeResult", targetNamespace = "")
    @RequestWrapper(localName = "divede", targetNamespace = "http://www.example.org/mywsdl/", className = "com.nantian.client2.DivedeType")
    @ResponseWrapper(localName = "divedeResponse", targetNamespace = "http://www.example.org/mywsdl/", className = "com.nantian.client2.DivedeResponse")
    public int divede(
        @WebParam(name = "num1", targetNamespace = "")
        int num1,
        @WebParam(name = "num2", targetNamespace = "")
        int num2);

}
