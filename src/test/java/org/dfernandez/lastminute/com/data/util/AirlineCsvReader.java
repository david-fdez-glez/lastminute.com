package org.dfernandez.lastminute.com.data.util;

import org.dfernandez.lastminute.com.model.Airline;

import java.math.BigDecimal;

public class AirlineCsvReader extends AbstractCsvReader<Airline> {

    @Override
    Airline unmarshall(String[] tokens) {
        return new Airline(tokens[0], tokens[1], new BigDecimal(tokens[2]));
    }
}
