package org.bp.car;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.3.2
 * 2021-02-13T18:00:12.135+01:00
 * Generated source version: 3.3.2
 *
 */
@WebServiceClient(name = "CarBookingService",
                  wsdlLocation = "file:/home/aznu/eclipse-workspace/car-booking/src/main/resources/car.wsdl",
                  targetNamespace = "http://www.bp.org/car/")
public class CarBookingService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.bp.org/car/", "CarBookingService");
    public final static QName CarBookingPort = new QName("http://www.bp.org/car/", "CarBookingPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/home/aznu/eclipse-workspace/car-booking/src/main/resources/car.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(CarBookingService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "file:/home/aznu/eclipse-workspace/car-booking/src/main/resources/car.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public CarBookingService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CarBookingService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CarBookingService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public CarBookingService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public CarBookingService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public CarBookingService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns CarBooking
     */
    @WebEndpoint(name = "CarBookingPort")
    public CarBooking getCarBookingPort() {
        return super.getPort(CarBookingPort, CarBooking.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CarBooking
     */
    @WebEndpoint(name = "CarBookingPort")
    public CarBooking getCarBookingPort(WebServiceFeature... features) {
        return super.getPort(CarBookingPort, CarBooking.class, features);
    }

}
