package org.dfernandez.lastminute.com.model;

import java.math.BigDecimal;

public class Airline {

    private String iataCode;

    private String name;

    private BigDecimal infantPrice;

    public Airline(String iataCode, String name, BigDecimal infantPrice) {
        this.iataCode = iataCode;
        this.name = name;
        this.infantPrice = infantPrice;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getInfantPrice() {
        return infantPrice;
    }

    public void setInfantPrice(BigDecimal infantPrice) {
        this.infantPrice = infantPrice;
    }
}
