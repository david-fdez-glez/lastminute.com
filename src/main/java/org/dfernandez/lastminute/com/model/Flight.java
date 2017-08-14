package org.dfernandez.lastminute.com.model;

import java.math.BigDecimal;
import java.util.function.Consumer;

public class Flight {

    private String origin;

    private String destination;

    private String flightCode;

    private Airline airline;

    private BigDecimal basePrice;

    public Flight(String origin, String destination, String flightCode, Airline airline, BigDecimal basePrice) {
        this.origin = origin;
        this.destination = destination;
        this.flightCode = flightCode;
        this.airline = airline;
        this.basePrice = basePrice;
    }


    public Flight(Consumer<Flight> builder) {
        builder.accept(this);
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }
}
