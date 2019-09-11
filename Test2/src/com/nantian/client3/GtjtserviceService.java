
package com.nantian.client3;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "GtjtserviceService", targetNamespace = "http://service.interf.gtjt.cux/", wsdlLocation = "http://erptest2.sdic.com.cn:8080/GtjtIntfService/service/WsExampleService?wsdl")
public class GtjtserviceService
    extends Service
{

    private final static URL GTJTSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException GTJTSERVICESERVICE_EXCEPTION;
    private final static QName GTJTSERVICESERVICE_QNAME = new QName("http://service.interf.gtjt.cux/", "GtjtserviceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://erptest2.sdic.com.cn:8080/GtjtIntfService/service/WsExampleService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        GTJTSERVICESERVICE_WSDL_LOCATION = url;
        GTJTSERVICESERVICE_EXCEPTION = e;
    }

    public GtjtserviceService() {
        super(__getWsdlLocation(), GTJTSERVICESERVICE_QNAME);
    }

    public GtjtserviceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), GTJTSERVICESERVICE_QNAME, features);
    }

    public GtjtserviceService(URL wsdlLocation) {
        super(wsdlLocation, GTJTSERVICESERVICE_QNAME);
    }

    public GtjtserviceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, GTJTSERVICESERVICE_QNAME, features);
    }

    public GtjtserviceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GtjtserviceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Igtjtservice
     */
    @WebEndpoint(name = "GtjtservicePort")
    public Igtjtservice getGtjtservicePort() {
        return super.getPort(new QName("http://service.interf.gtjt.cux/", "GtjtservicePort"), Igtjtservice.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Igtjtservice
     */
    @WebEndpoint(name = "GtjtservicePort")
    public Igtjtservice getGtjtservicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.interf.gtjt.cux/", "GtjtservicePort"), Igtjtservice.class, features);
    }

    private static URL __getWsdlLocation() {
        if (GTJTSERVICESERVICE_EXCEPTION!= null) {
            throw GTJTSERVICESERVICE_EXCEPTION;
        }
        return GTJTSERVICESERVICE_WSDL_LOCATION;
    }

}