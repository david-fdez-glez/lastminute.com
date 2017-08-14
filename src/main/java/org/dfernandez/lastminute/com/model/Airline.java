package org.dfernandez.lastminute.com.model;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airline airline = (Airline) o;
        return Objects.equals(iataCode, airline.iataCode) &&
                Objects.equals(infantPrice, airline.infantPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iataCode, name, infantPrice);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Airline{");
        sb.append("iataCode='").append(iataCode).append('\'');
        sb.append(", name=").append(name);
        sb.append(", infantPrice=").append(infantPrice);
        sb.append('}');
        return sb.toString();
    }
}
