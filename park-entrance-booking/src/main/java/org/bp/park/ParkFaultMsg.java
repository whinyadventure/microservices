package org.bp.park;

import org.bp.types.Fault;


public class ParkFaultMsg extends Exception {
	
	private Fault parkFault;
	
	public Fault getParkFault() {
		return parkFault;
	}

	public void setParkFault(Fault parkFault) {
		this.parkFault = parkFault;
	}

    public ParkFaultMsg() {
        super();
    }

    public ParkFaultMsg(String message) {
        super(message);
    }

    public ParkFaultMsg(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public ParkFaultMsg(String message, Fault parkFault) {
        super(message);
        this.parkFault = parkFault;
    }

    public ParkFaultMsg(String message, Fault parkFault, java.lang.Throwable cause) {
        super(message, cause);
        this.parkFault = parkFault;
    }

    public Fault getFaultInfo() {
        return this.parkFault;
    }

}
