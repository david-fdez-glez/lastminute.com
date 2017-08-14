package org.dfernandez.lastminute.com.utils;

import org.dfernandez.lastminute.com.model.Airline;

import java.math.BigDecimal;

public class AirlineCsvReader extends AbstractCsvReader {

    @Override
    Airline unmarshall(String[] tokens) {
        Airline airport = new Airline(tokens[0], tokens[1], new BigDecimal(tokens[2]));
        return airport;
    }
}
