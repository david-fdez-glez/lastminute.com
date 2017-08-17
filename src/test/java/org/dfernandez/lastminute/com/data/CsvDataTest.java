package org.dfernandez.lastminute.com.data;

import org.dfernandez.lastminute.com.model.Airline;
import org.dfernandez.lastminute.com.model.Airport;
import org.dfernandez.lastminute.com.model.Flight;
import org.dfernandez.lastminute.com.data.util.AirlineCsvReader;
import org.dfernandez.lastminute.com.data.util.AirportCsvReader;
import org.dfernandez.lastminute.com.data.util.FlightsCsvReader;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CsvDataTest {

    private final ClassLoader classLoader = getClass().getClassLoader();
    private final static String AIRPORT_FILE_NAME = "airports.csv";
    private final static String AIRLINES_FILE_NAME = "airlines.csv";
    private final static String FLIGHTS_FILE_NAME = "flights.csv";

    /**
     * Useful to build Flight Object with Airline
     */
    private Map<String, Airline> airlineMap;

    @Before
    public void setUp() {
        airlineMap = new HashMap<>();
        airlineMap.put("IB", new Airline("IB", "Iberia", new BigDecimal(10)));
        airlineMap.put("BA", new Airline("BA", "British Airways", new BigDecimal(15)));
        airlineMap.put("LH", new Airline("LH", "Lufthansa", new BigDecimal(7)));
        airlineMap.put("FR", new Airline("FR", "Ryanair", new BigDecimal(20)));
        airlineMap.put("VY", new Airline("VY", "Vueling", new BigDecimal(10)));
        airlineMap.put("TK", new Airline("TK", "Turkish Airlines", new BigDecimal(5)));
        airlineMap.put("U2", new Airline("U2", "Easyjet", new BigDecimal(19.9)));
    }

    @Test
    public void airportDataTest() {
        AirportCsvReader airportCsvReader = new AirportCsvReader();

        File file = new File(classLoader.getResource(AIRPORT_FILE_NAME).getFile());

        List<Airport> airports = airportCsvReader.readCsvFile(file);

        List<Airport> expected = new ArrayList<>();
        expected.add(new Airport("MAD", "Madrid"));
        expected.add(new Airport("BCN", "Barcelona"));
        expected.add(new Airport("LHR", "London"));
        expected.add(new Airport("CDG", "Paris"));
        expected.add(new Airport("FRA", "Frankfurt"));
        expected.add(new Airport("IST", "Istanbul"));
        expected.add(new Airport("AMS", "Amsterdam"));
        expected.add(new Airport("FCO", "Rome"));
        expected.add(new Airport("CPH", "Copenhagen"));

        assertThat(airports, equalTo(expected));
    }

    @Test
    public void airlineDataTest() {
        AirlineCsvReader airlineCsvReader = new AirlineCsvReader();

        File file = new File(classLoader.getResource(AIRLINES_FILE_NAME).getFile());

        List<Airline> airlines = airlineCsvReader.readCsvFile(file);

        assertThat(airlines, hasSize(7));

        assertThat(airlines, hasItems(airlineMap.get("FR"), airlineMap.get("TK")));
    }


    @Test()
    public void flightsDataTest() {
        FlightsCsvReader flightsCsvReader = new FlightsCsvReader(airlineMap);

        File file = new File(classLoader.getResource(FLIGHTS_FILE_NAME).getFile());

        List<Flight> flights = flightsCsvReader.readCsvFile(file);

        assertThat(flights, hasSize(89));

        assertThat(flights, hasItems(new Flight(flight -> {
                    flight.setOrigin("CPH");
                    flight.setDestination("FRA");
                    flight.setFlightCode("IB2818");
                    flight.setAirline(airlineMap.get("IB"));
                    flight.setBasePrice(new BigDecimal(186));
                }),
                new Flight(flight -> {
                    flight.setOrigin("LHR");
                    flight.setDestination("AMS");
                    flight.setFlightCode("BA9561");
                    flight.setAirline(airlineMap.get("BA"));
                    flight.setBasePrice(new BigDecimal(253));
                })));


    }


}
