package org.examples.cctransaction;

public class AllowedCountry {
    String ccNumber;
    String country;

    public AllowedCountry(){}

    public AllowedCountry(String ccNumber, String country){
        this.ccNumber = ccNumber;
        this.country = country;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String toString(){
        return "Country " + this.country + " allowed for ccNumber " + this.ccNumber;
    }
}
