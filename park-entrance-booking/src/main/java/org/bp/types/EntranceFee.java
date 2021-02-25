
package org.bp.types;

import javax.xml.datatype.XMLGregorianCalendar;

public class EntranceFee {

    protected String park;
    protected XMLGregorianCalendar from;
    protected XMLGregorianCalendar to;

	public String getPark() {
		return park;
	}
	public void setPark(String park) {
		this.park = park;
	}
	public XMLGregorianCalendar getFrom() {
		return from;
	}
	public void setFrom(XMLGregorianCalendar from) {
		this.from = from;
	}
	public XMLGregorianCalendar getTo() {
		return to;
	}
	public void setTo(XMLGregorianCalendar to) {
		this.to = to;
	}


}
