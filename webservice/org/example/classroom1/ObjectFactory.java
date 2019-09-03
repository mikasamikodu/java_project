//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2018.08.20 时间 10:43:32 AM CST 
//


package org.example.classroom1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.example.classroom1 package. 
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

    private final static QName _Classroom1_QNAME = new QName("http://www.example.org/classroom1", "classroom1");
    private final static QName _Student_QNAME = new QName("http://www.example.org/classroom1", "student");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example.classroom1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Classroom1Type }
     * 
     */
    public Classroom1Type createClassroom1Type() {
        return new Classroom1Type();
    }

    /**
     * Create an instance of {@link Student1Type }
     * 
     */
    public Student1Type createStudent1Type() {
        return new Student1Type();
    }

    /**
     * Create an instance of {@link Classroom1Type.Student }
     * 
     */
    public Classroom1Type.Student createClassroom1TypeStudent() {
        return new Classroom1Type.Student();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Classroom1Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/classroom1", name = "classroom1")
    public JAXBElement<Classroom1Type> createClassroom1(Classroom1Type value) {
        return new JAXBElement<Classroom1Type>(_Classroom1_QNAME, Classroom1Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Student1Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/classroom1", name = "student")
    public JAXBElement<Student1Type> createStudent(Student1Type value) {
        return new JAXBElement<Student1Type>(_Student_QNAME, Student1Type.class, null, value);
    }

}
