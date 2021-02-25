package org.bp.adventureclient.model;


public class PaymentCard {
  
  private String name = null;
  private String validTo = null;
  private String number = null;

  public PaymentCard name(String name) {
    this.name = name;
    return this;
  }
 
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PaymentCard validTo(String validTo) {
    this.validTo = validTo;
    return this;
  }
 
  public String getValidTo() {
    return validTo;
  }

  public void setValidTo(String validTo) {
    this.validTo = validTo;
  }

  public PaymentCard number(String number) {
    this.number = number;
    return this;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

}
