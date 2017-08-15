package org.dfernandez.lastminute.com.utils;

import org.dfernandez.lastminute.com.model.Airport;

public class AirportCsvReader extends AbstractCsvReader<Airport> {

    @Override
    Airport unmarshall(String[] tokens) {
        return  new Airport(tokens[0], tokens[1]);
    }
}
