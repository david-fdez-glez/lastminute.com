package org.dfernandez.lastminute.com.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class FlightTicket {

    //FlightTicket uniqueId
    private String uniqueID;
    
    private String origin;

    private String destination;

    private String flightCode;

    private Airline airline;

    private int numAdults;

    private int numChild;

    private int numInfants;
    
    private BigDecimal totalPrice;

    public FlightTicket(String origin, String destination, String flightCode, Airline airline, int numAdults, int numChild, int numInfants, BigDecimal totalPrice) {
        this.origin = origin;
        this.destination = destination;
        this.flightCode = flightCode;
        this.airline = airline;
        this.numAdults = numAdults;
        this.numChild = numChild;
        this.numInfants = numInfants;
        this.totalPrice = totalPrice;
        uniqueID = UUID.randomUUID().toString();
    }

    public String getUniqueID() {
        return uniqueID;
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

    public int getNumAdults() {
        return numAdults;
    }

    public void setNumAdults(int numAdults) {
        this.numAdults = numAdults;
    }

    public int getNumChild() {
        return numChild;
    }

    public void setNumChild(int numChild) {
        this.numChild = numChild;
    }

    public int getNumInfants() {
        return numInfants;
    }

    public void setNumInfants(int numInfants) {
        this.numInfants = numInfants;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightTicket flightTicket = (FlightTicket) o;
        return Objects.equals(uniqueID, flightTicket.uniqueID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueID);
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FlightTicket{");
        sb.append("uniqueID=").append(uniqueID);
        sb.append("origin=").append(origin);
        sb.append(", destination=").append(destination);
        sb.append(", flightCode=").append(flightCode);
        sb.append(", ").append(airline);
        sb.append(", numAdults= ").append(numAdults);
        sb.append(", numChild= ").append(numChild);
        sb.append(", numInfants= ").append(numInfants);
        sb.append(", totalPrice= ").append(totalPrice);
        sb.append('}');
        return sb.toString();
    }
}
