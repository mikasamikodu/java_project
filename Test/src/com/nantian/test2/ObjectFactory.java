
package com.nantian.test2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.nantian.test2 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MinResponse_QNAME = new QName("http://data.nantian.com/", "minResponse");
    private final static QName _AddResponse_QNAME = new QName("http://data.nantian.com/", "addResponse");
    private final static QName _Add_QNAME = new QName("http://data.nantian.com/", "add");
    private final static QName _Min_QNAME = new QName("http://data.nantian.com/", "min");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.nantian.test2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Add }
     * 
     */
    public Add createAdd() {
        return new Add();
    }

    /**
     * Create an instance of {@link Min }
     * 
     */
    public Min createMin() {
        return new Min();
    }

    /**
     * Create an instance of {@link AddResponse }
     * 
     */
    public AddResponse createAddResponse() {
        return new AddResponse();
    }

    /**
     * Create an instance of {@link MinResponse }
     * 
     */
    public MinResponse createMinResponse() {
        return new MinResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MinResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://data.nantian.com/", name = "minResponse")
    public JAXBElement<MinResponse> createMinResponse(MinResponse value) {
        return new JAXBElement<MinResponse>(_MinResponse_QNAME, MinResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://data.nantian.com/", name = "addResponse")
    public JAXBElement<AddResponse> createAddResponse(AddResponse value) {
        return new JAXBElement<AddResponse>(_AddResponse_QNAME, AddResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Add }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://data.nantian.com/", name = "add")
    public JAXBElement<Add> createAdd(Add value) {
        return new JAXBElement<Add>(_Add_QNAME, Add.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Min }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://data.nantian.com/", name = "min")
    public JAXBElement<Min> createMin(Min value) {
        return new JAXBElement<Min>(_Min_QNAME, Min.class, null, value);
    }

}
