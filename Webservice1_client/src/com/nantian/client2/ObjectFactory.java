
package com.nantian.client2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.nantian.client2 package. 
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

    private final static QName _Add_QNAME = new QName("http://www.example.org/mywsdl/", "add");
    private final static QName _AddResponse_QNAME = new QName("http://www.example.org/mywsdl/", "addResponse");
    private final static QName _Divede_QNAME = new QName("http://www.example.org/mywsdl/", "divede");
    private final static QName _DivedeResponse_QNAME = new QName("http://www.example.org/mywsdl/", "divedeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.nantian.client2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddType }
     * 
     */
    public AddType createAddType() {
        return new AddType();
    }

    /**
     * Create an instance of {@link AddResponse }
     * 
     */
    public AddResponse createAddResponse() {
        return new AddResponse();
    }

    /**
     * Create an instance of {@link DivedeType }
     * 
     */
    public DivedeType createDivedeType() {
        return new DivedeType();
    }

    /**
     * Create an instance of {@link DivedeResponse }
     * 
     */
    public DivedeResponse createDivedeResponse() {
        return new DivedeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/mywsdl/", name = "add")
    public JAXBElement<AddType> createAdd(AddType value) {
        return new JAXBElement<AddType>(_Add_QNAME, AddType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/mywsdl/", name = "addResponse")
    public JAXBElement<AddResponse> createAddResponse(AddResponse value) {
        return new JAXBElement<AddResponse>(_AddResponse_QNAME, AddResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DivedeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/mywsdl/", name = "divede")
    public JAXBElement<DivedeType> createDivede(DivedeType value) {
        return new JAXBElement<DivedeType>(_Divede_QNAME, DivedeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DivedeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/mywsdl/", name = "divedeResponse")
    public JAXBElement<DivedeResponse> createDivedeResponse(DivedeResponse value) {
        return new JAXBElement<DivedeResponse>(_DivedeResponse_QNAME, DivedeResponse.class, null, value);
    }

}
