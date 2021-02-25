package org.bp.adventureclient.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class EntranceFee {

	@DateTimeFormat(pattern = "yyyy-mm-dd")
    protected Date from;
    protected String park;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    protected Date to;


    public Date getFrom() {
        return from;
    }


    public void setFrom(Date value) {
        this.from = value;
    }


    public String getPark() {
        return park;
    }


    public void setPark(String value) {
        this.park = value;
    }


    public Date getTo() {
        return to;
    }


    public void setTo(Date value) {
        this.to = value;
    }

}
