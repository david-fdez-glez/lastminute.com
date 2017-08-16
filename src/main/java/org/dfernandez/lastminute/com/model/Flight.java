package org.dfernandez.lastminute.com.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Consumer;

public class Flight {

    private String origin;

    private String destination;

    private String flightCode;

    private Airline airline;

    private BigDecimal basePrice;

    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(origin, flight.origin) &&
                Objects.equals(destination, flight.destination) &&
                Objects.equals(flightCode, flight.flightCode) &&
                Objects.equals(airline, flight.airline) &&
                Objects.equals(basePrice, flight.basePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination, flightCode, airline, basePrice);
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Flight{");
        sb.append("origin=").append(origin);
        sb.append(", destination=").append(destination);
        sb.append(", flightCode=").append(flightCode);
        sb.append(", ").append(airline);
        sb.append(", basePrice= ").append(basePrice);
        sb.append('}');
        return sb.toString();
    }
}
