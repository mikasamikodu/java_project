
package com.nantian.test3;

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
@WebServiceClient(name = "DataImplService", targetNamespace = "http://data.nantian.com/", wsdlLocation = "http://localhost:9090/service?wsdl")
public class DataImplService
    extends Service
{

    private final static URL DATAIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException DATAIMPLSERVICE_EXCEPTION;
    private final static QName DATAIMPLSERVICE_QNAME = new QName("http://data.nantian.com/", "DataImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:9090/service?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        DATAIMPLSERVICE_WSDL_LOCATION = url;
        DATAIMPLSERVICE_EXCEPTION = e;
    }

    public DataImplService() {
        super(__getWsdlLocation(), DATAIMPLSERVICE_QNAME);
    }

    public DataImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), DATAIMPLSERVICE_QNAME, features);
    }

    public DataImplService(URL wsdlLocation) {
        super(wsdlLocation, DATAIMPLSERVICE_QNAME);
    }

    public DataImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, DATAIMPLSERVICE_QNAME, features);
    }

    public DataImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DataImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Data
     */
    @WebEndpoint(name = "DataImplPort")
    public Data getDataImplPort() {
        return super.getPort(new QName("http://data.nantian.com/", "DataImplPort"), Data.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Data
     */
    @WebEndpoint(name = "DataImplPort")
    public Data getDataImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://data.nantian.com/", "DataImplPort"), Data.class, features);
    }

    private static URL __getWsdlLocation() {
        if (DATAIMPLSERVICE_EXCEPTION!= null) {
            throw DATAIMPLSERVICE_EXCEPTION;
        }
        return DATAIMPLSERVICE_WSDL_LOCATION;
    }

}
