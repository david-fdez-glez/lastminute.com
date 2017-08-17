package org.dfernandez.lastminute.com.dto;


import java.math.BigDecimal;
import java.util.Objects;

public class SearchResponse {

    private String flightCode;

    private BigDecimal price;

    public SearchResponse(String flightCode, BigDecimal price) {
        this.flightCode = flightCode;
        this.price = price;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResponse response = (SearchResponse) o;
        return Objects.equals(flightCode, response.flightCode) &&
                Objects.equals(price, response.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightCode, price);
    }

}
