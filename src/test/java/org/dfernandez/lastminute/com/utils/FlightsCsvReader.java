package org.dfernandez.lastminute.com.utils;

import org.dfernandez.lastminute.com.model.Airline;
import org.dfernandez.lastminute.com.model.Flight;

import java.math.BigDecimal;
import java.util.Map;

public class FlightsCsvReader extends AbstractCsvReader<Flight> {

    /**
     * Useful to build Flight Object with Airline
     */
    private Map<String, Airline> airlineMap;

    public FlightsCsvReader(Map<String, Airline> airlineMap) {
        this.airlineMap = airlineMap;
    }

    @Override
    Flight unmarshall(String[] tokens) {
        Flight flight = new Flight( f -> {
            f.setOrigin(tokens[0]);
            f.setDestination(tokens[1]);
            f.setFlightCode(tokens[2]);
            f.setBasePrice(new BigDecimal(tokens[3]));
        });

        if(airlineMap != null) {
            String iataCode = parseIataCodeFromFlightCode(flight.getFlightCode());
            if(airlineMap.containsKey(iataCode)) {
                flight.setAirline(airlineMap.get(iataCode));
            }
        }
        return flight;
    }


    private String parseIataCodeFromFlightCode(String flightCode) {
        return flightCode.length() < 2 ? flightCode : flightCode.substring(0,2);
    }
}
