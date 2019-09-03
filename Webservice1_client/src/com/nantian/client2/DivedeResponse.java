
package com.nantian.client2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>divedeResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="divedeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="divedeResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "divedeResponse", propOrder = {
    "divedeResult"
})
public class DivedeResponse {

    protected int divedeResult;

    /**
     * 获取divedeResult属性的值。
     * 
     */
    public int getDivedeResult() {
        return divedeResult;
    }

    /**
     * 设置divedeResult属性的值。
     * 
     */
    public void setDivedeResult(int value) {
        this.divedeResult = value;
    }

}
