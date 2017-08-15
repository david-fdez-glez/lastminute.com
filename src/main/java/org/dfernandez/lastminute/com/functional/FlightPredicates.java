package org.dfernandez.lastminute.com.functional;

import org.dfernandez.lastminute.com.model.Flight;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlightPredicates {

    public static Predicate<Flight> matchRoute(String from, String to) {
        return flight ->  flight.getOrigin().equalsIgnoreCase(from) && flight.getDestination().equalsIgnoreCase(to);
    }
  
    public static List<Flight> filterFlights(List<Flight> data, Predicate<Flight> predicate) {
        return data.stream().filter(predicate).collect(Collectors.toList());
    }

}
