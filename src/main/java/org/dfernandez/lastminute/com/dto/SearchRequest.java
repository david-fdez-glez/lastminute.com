package org.dfernandez.lastminute.com.dto;

import java.util.Date;

public class SearchRequest {

    private String airportOrigin;

    private String airportDestination;

    private Date departureDate;

    private int numAdults;

    private int numChild;

    private int numInfants;

    public SearchRequest(String airportOrigin, String airportDestination, Date departureDate, int numAdults, int numChilds, int numIfants) {
        this.airportOrigin = airportOrigin;
        this.airportDestination = airportDestination;
        this.departureDate = departureDate;
        this.numAdults = numAdults;
        this.numChild = numChilds;
        this.numInfants = numIfants;
    }

    public String getAirportOrigin() {
        return airportOrigin;
    }

    public void setAirportOrigin(String airportOrigin) {
        this.airportOrigin = airportOrigin;
    }

    public String getAirportDestination() {
        return airportDestination;
    }

    public void setAirportDestination(String airportDestination) {
        this.airportDestination = airportDestination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
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
}
