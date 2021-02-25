
package org.bp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.bp.types.BookingInfo;
import org.bp.types.Fault;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.bp package. 
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

    private final static QName _BookCarResponse_QNAME = new QName("http://www.bp.org", "bookCarResponse");
    private final static QName _CancelBookingResponse_QNAME = new QName("http://www.bp.org", "cancelBookingResponse");
    private final static QName _CarFault_QNAME = new QName("http://www.bp.org", "carFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.bp
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BookCarRequest }
     * 
     */
    public BookCarRequest createBookCarRequest() {
        return new BookCarRequest();
    }

    /**
     * Create an instance of {@link CancelBookingRequest }
     * 
     */
    public CancelBookingRequest createCancelBookingRequest() {
        return new CancelBookingRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookingInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BookingInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bp.org", name = "bookCarResponse")
    public JAXBElement<BookingInfo> createBookCarResponse(BookingInfo value) {
        return new JAXBElement<BookingInfo>(_BookCarResponse_QNAME, BookingInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookingInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BookingInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bp.org", name = "cancelBookingResponse")
    public JAXBElement<BookingInfo> createCancelBookingResponse(BookingInfo value) {
        return new JAXBElement<BookingInfo>(_CancelBookingResponse_QNAME, BookingInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Fault }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Fault }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bp.org", name = "carFault")
    public JAXBElement<Fault> createCarFault(Fault value) {
        return new JAXBElement<Fault>(_CarFault_QNAME, Fault.class, null, value);
    }

}
