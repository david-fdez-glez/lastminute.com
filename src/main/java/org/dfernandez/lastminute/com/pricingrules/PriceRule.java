package org.dfernandez.lastminute.com.pricingrules;

import org.dfernandez.lastminute.com.dto.SearchRequest;
import org.dfernandez.lastminute.com.model.Flight;
import org.dfernandez.lastminute.com.model.FlightTicket;


public interface PriceRule {

    FlightTicket apply(SearchRequest searchRequest, Flight flight);
}
