package org.dfernandez.lastminute.com.data;

import org.dfernandez.lastminute.com.model.Airline;
import org.dfernandez.lastminute.com.model.Airport;
import org.dfernandez.lastminute.com.utils.AirlineCsvReader;
import org.dfernandez.lastminute.com.utils.AirportCsvReader;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DataTest {

    private final ClassLoader classLoader = getClass().getClassLoader();
    private final static String AIRPORT_FILE_NAME = "airports.csv";
    private final static String AIRLINES_FILE_NAME = "airlines.csv";

    @Before
    public void setUp() {

    }

    @Test
    public void airportDataTest() {
        AirportCsvReader airportCsvReader = new AirportCsvReader();

        File file = new File(classLoader.getResource(AIRPORT_FILE_NAME).getFile());

        List<Airport>  airports = airportCsvReader.readCsvFile(file);

        List<Airport> expected = new ArrayList<>();
        expected.add( new Airport("MAD", "Madrid"));
        expected.add( new Airport("BCN", "Barcelona"));
        expected.add( new Airport("LHR", "London"));
        expected.add( new Airport("CDG", "Paris"));
        expected.add( new Airport("FRA", "Frankfurt"));
        expected.add( new Airport("IST", "Istanbul"));
        expected.add( new Airport("AMS", "Amsterdam"));
        expected.add( new Airport("FCO", "Rome"));
        expected.add( new Airport("CPH", "Copenhagen"));

        assertThat(airports, equalTo(expected));
    }

    @Test
    public void airlineDataTest() {
        AirlineCsvReader airlineCsvReader = new AirlineCsvReader();

        File file = new File(classLoader.getResource(AIRLINES_FILE_NAME).getFile());

        List<Airline>  airlines = airlineCsvReader.readCsvFile(file);

        assertThat(airlines, hasSize(7));

        assertThat(airlines, hasItems(new Airline("FR", "Ryanair", new BigDecimal("20")),
                new Airline("TK", "Turkish Airlines", new BigDecimal("5"))));
    }
    
}
