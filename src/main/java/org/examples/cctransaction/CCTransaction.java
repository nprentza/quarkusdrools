package org.examples.cctransaction;

public class CCTransaction {
    String ccNumber;
    float amount;
    String country;
    String status;

    public CCTransaction(){}

    public CCTransaction(String ccNumber, String country){
        this.ccNumber = ccNumber;
        this.country = country;
        this.status = "new";
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setStatus(String status){this.status=status;}
    public String getStatus(){return this.status;}

    public String toString(){
        return "CCTransaction: " + this.ccNumber + ", " + this.country;
    }
}
