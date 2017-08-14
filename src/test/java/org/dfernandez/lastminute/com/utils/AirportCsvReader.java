package org.dfernandez.lastminute.com.utils;

import org.dfernandez.lastminute.com.model.Airport;

public class AirportCsvReader extends AbstractCsvReader {

    @Override
    Airport unmarshall(String[] tokens) {
        Airport airport = new Airport(tokens[0], tokens[1]);
        return airport;
    }
}
