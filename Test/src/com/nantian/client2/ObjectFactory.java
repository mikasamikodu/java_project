
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

    private final static QName _Add_QNAME = new QName("http://dao.nantian.com/", "add");
    private final static QName _AddResponse_QNAME = new QName("http://dao.nantian.com/", "addResponse");
    private final static QName _Delete_QNAME = new QName("http://dao.nantian.com/", "delete");
    private final static QName _DeleteResponse_QNAME = new QName("http://dao.nantian.com/", "deleteResponse");
    private final static QName _GetErpStr_QNAME = new QName("http://dao.nantian.com/", "getErpStr");
    private final static QName _GetErpStrResponse_QNAME = new QName("http://dao.nantian.com/", "getErpStrResponse");
    private final static QName _Select1_QNAME = new QName("http://dao.nantian.com/", "select1");
    private final static QName _Select1Response_QNAME = new QName("http://dao.nantian.com/", "select1Response");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.nantian.client2
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
     * Create an instance of {@link AddResponse }
     * 
     */
    public AddResponse createAddResponse() {
        return new AddResponse();
    }

    /**
     * Create an instance of {@link Delete }
     * 
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link DeleteResponse }
     * 
     */
    public DeleteResponse createDeleteResponse() {
        return new DeleteResponse();
    }

    /**
     * Create an instance of {@link GetErpStr }
     * 
     */
    public GetErpStr createGetErpStr() {
        return new GetErpStr();
    }

    /**
     * Create an instance of {@link GetErpStrResponse }
     * 
     */
    public GetErpStrResponse createGetErpStrResponse() {
        return new GetErpStrResponse();
    }

    /**
     * Create an instance of {@link Select1 }
     * 
     */
    public Select1 createSelect1() {
        return new Select1();
    }

    /**
     * Create an instance of {@link Select1Response }
     * 
     */
    public Select1Response createSelect1Response() {
        return new Select1Response();
    }

    /**
     * Create an instance of {@link Student }
     * 
     */
    public Student createStudent() {
        return new Student();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Add }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dao.nantian.com/", name = "add")
    public JAXBElement<Add> createAdd(Add value) {
        return new JAXBElement<Add>(_Add_QNAME, Add.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dao.nantian.com/", name = "addResponse")
    public JAXBElement<AddResponse> createAddResponse(AddResponse value) {
        return new JAXBElement<AddResponse>(_AddResponse_QNAME, AddResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dao.nantian.com/", name = "delete")
    public JAXBElement<Delete> createDelete(Delete value) {
        return new JAXBElement<Delete>(_Delete_QNAME, Delete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dao.nantian.com/", name = "deleteResponse")
    public JAXBElement<DeleteResponse> createDeleteResponse(DeleteResponse value) {
        return new JAXBElement<DeleteResponse>(_DeleteResponse_QNAME, DeleteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetErpStr }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dao.nantian.com/", name = "getErpStr")
    public JAXBElement<GetErpStr> createGetErpStr(GetErpStr value) {
        return new JAXBElement<GetErpStr>(_GetErpStr_QNAME, GetErpStr.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetErpStrResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dao.nantian.com/", name = "getErpStrResponse")
    public JAXBElement<GetErpStrResponse> createGetErpStrResponse(GetErpStrResponse value) {
        return new JAXBElement<GetErpStrResponse>(_GetErpStrResponse_QNAME, GetErpStrResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Select1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dao.nantian.com/", name = "select1")
    public JAXBElement<Select1> createSelect1(Select1 value) {
        return new JAXBElement<Select1>(_Select1_QNAME, Select1 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Select1Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dao.nantian.com/", name = "select1Response")
    public JAXBElement<Select1Response> createSelect1Response(Select1Response value) {
        return new JAXBElement<Select1Response>(_Select1Response_QNAME, Select1Response.class, null, value);
    }

}
