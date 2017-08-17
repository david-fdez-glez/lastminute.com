package org.dfernandez.lastminute.com.service;

import org.dfernandez.lastminute.com.dto.SearchRequest;
import org.dfernandez.lastminute.com.dto.SearchResponse;
import org.dfernandez.lastminute.com.functional.FlightPredicates;
import org.dfernandez.lastminute.com.model.Flight;
import org.dfernandez.lastminute.com.model.FlightTicket;
import org.dfernandez.lastminute.com.pricingrules.PriceRule;

import java.util.List;
import java.util.stream.Collectors;

public class SearchEngineImpl implements SearchEngine {

    private final PriceRule priceRule;
    private List<Flight> flightsData;

    public SearchEngineImpl(PriceRule priceRule, List<Flight> flightsData) {
        this.priceRule = priceRule;
        this.flightsData = flightsData;
    }

    public void setFlightsData(List<Flight> flightsData) {
        this.flightsData = flightsData;
    }

    @Override
    public List<SearchResponse> search(SearchRequest request) {

        List<Flight> flights =  FlightPredicates.filterFlights(flightsData, FlightPredicates.matchRoute(request.getAirportOrigin(), request.getAirportDestination()));
        List<FlightTicket> flightTickets = flights.stream().map(flight -> priceRule.apply(request, flight)).collect(Collectors.toList());

        return flightTickets.stream().map( flightTicket -> new SearchResponse( flightTicket.getFlightCode(), flightTicket.getTotalPrice())).collect(Collectors.toList());
    }
}
