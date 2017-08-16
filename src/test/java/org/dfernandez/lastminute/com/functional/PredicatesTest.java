package org.dfernandez.lastminute.com.functional;

import org.dfernandez.lastminute.com.model.Airline;
import org.dfernandez.lastminute.com.model.Airport;
import org.dfernandez.lastminute.com.model.Flight;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PredicatesTest {

    private List<Flight> emptyList;

    private List<Flight> flightList;

    private Airline lufthansa;
    private Airline turkish;
    private Airport amsterdam;
    private Airport frankfurt;

    private Flight flight1;
    private Flight flight2;
    private Flight flight3;


    @Before
    public void setUp() {
        emptyList = new ArrayList<>();
        lufthansa = new Airline("LH", "Lufthansa", new BigDecimal(7));
        turkish =  new Airline("TK", "Turkish Airlines", new BigDecimal(5));
        amsterdam = new Airport("AMS", "Amsterdam");
        frankfurt = new Airport("FRA","Frankfurt");
        flight1 = new Flight(flight -> {
            flight.setOrigin("AMS");
            flight.setDestination("FRA");
            flight.setFlightCode("TK2372");
            flight.setAirline(turkish);
            flight.setBasePrice(new BigDecimal(197));
        });
        flight2 = new Flight(flight -> {
            flight.setOrigin("AMS");
            flight.setDestination("FRA");
            flight.setFlightCode("LH5909");
            flight.setAirline(lufthansa);
            flight.setBasePrice(new BigDecimal(113));
        });  
        flight3 = new Flight(flight -> {
            flight.setOrigin("AMS");
            flight.setDestination("CPH");
            flight.setFlightCode("TK4927");
            flight.setAirline(turkish);
            flight.setBasePrice(new BigDecimal(290));
        });

       flightList = new ArrayList<>();
       flightList.addAll(Arrays.asList(flight1, flight2, flight3));
      
   }


   @Test
   public void emptyList() {
        List<Flight> result = FlightPredicates.filterFlights(emptyList, FlightPredicates.matchRoute(frankfurt.getIataCode(),amsterdam.getIataCode()));
        assertThat(result, is(empty()));
   }

   @Test
   public void emptyResults() {
        List<Flight> result = FlightPredicates.filterFlights(flightList, FlightPredicates.matchRoute("CDG", "FRA"));
        assertThat(result, is(empty()));
   }

   @Test
    public void matchFlights() {
       List<Flight> result = FlightPredicates.filterFlights(flightList, FlightPredicates.matchRoute(amsterdam.getIataCode(),frankfurt.getIataCode()));
       List<Flight> expected = new ArrayList<>();
       expected.addAll(Arrays.asList(flight1, flight2));
       assertThat(result, equalTo(expected));
    }
}
