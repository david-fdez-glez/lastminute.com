package org.dfernandez.lastminute.com.model;

import java.util.Objects;

public class Airport {

    private String iataCode;

    private String city;

    public Airport(String iataCode, String city) {
        this.iataCode = iataCode;
        this.city = city;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return Objects.equals(iataCode, airport.iataCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iataCode, city);
    }

    @Override
    public String toString() {
        return "Airport{" + iataCode + ", " + city + "}";
    }


}
