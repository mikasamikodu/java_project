//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2018.08.20 时间 10:43:32 AM CST 
//


package org.example.classroom1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>classroom1Type complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="classroom1Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="grade" type="{http://www.example.org/classroom1}gradeType"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="student">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="student1" type="{http://www.example.org/classroom1}student1Type"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "classroom1Type", propOrder = {
    "grade",
    "name",
    "student"
})
public class Classroom1Type {

    protected int grade;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected Classroom1Type.Student student;

    /**
     * 获取grade属性的值。
     * 
     */
    public int getGrade() {
        return grade;
    }

    /**
     * 设置grade属性的值。
     * 
     */
    public void setGrade(int value) {
        this.grade = value;
    }

    /**
     * 获取name属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取student属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Classroom1Type.Student }
     *     
     */
    public Classroom1Type.Student getStudent() {
        return student;
    }

    /**
     * 设置student属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Classroom1Type.Student }
     *     
     */
    public void setStudent(Classroom1Type.Student value) {
        this.student = value;
    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="student1" type="{http://www.example.org/classroom1}student1Type"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "student1"
    })
    public static class Student {

        @XmlElement(required = true)
        protected Student1Type student1;

        /**
         * 获取student1属性的值。
         * 
         * @return
         *     possible object is
         *     {@link Student1Type }
         *     
         */
        public Student1Type getStudent1() {
            return student1;
        }

        /**
         * 设置student1属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link Student1Type }
         *     
         */
        public void setStudent1(Student1Type value) {
            this.student1 = value;
        }

    }

}
