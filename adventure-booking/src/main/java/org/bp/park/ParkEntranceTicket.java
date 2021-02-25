//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.18 at 02:16:15 PM CET 
//


package org.bp.park;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for parkEntranceTicket complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parkEntranceTicket"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="fee" type="{http://park.bp.org/}entranceFee" minOccurs="0"/&gt;
 *         &lt;element name="person" type="{http://park.bp.org/}person" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parkEntranceTicket", propOrder = {
    "fee",
    "person"
})
public class ParkEntranceTicket {

    protected EntranceFee fee;
    protected Person person;

    /**
     * Gets the value of the fee property.
     * 
     * @return
     *     possible object is
     *     {@link EntranceFee }
     *     
     */
    public EntranceFee getFee() {
        return fee;
    }

    /**
     * Sets the value of the fee property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntranceFee }
     *     
     */
    public void setFee(EntranceFee value) {
        this.fee = value;
    }

    /**
     * Gets the value of the person property.
     * 
     * @return
     *     possible object is
     *     {@link Person }
     *     
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Sets the value of the person property.
     * 
     * @param value
     *     allowed object is
     *     {@link Person }
     *     
     */
    public void setPerson(Person value) {
        this.person = value;
    }

}
