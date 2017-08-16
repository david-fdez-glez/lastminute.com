package org.dfernandez.lastminute.com.pricingrules;

import org.dfernandez.lastminute.com.dto.SearchRequest;
import org.dfernandez.lastminute.com.model.Flight;
import org.dfernandez.lastminute.com.model.FlightTicket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.NavigableMap;
import java.util.TreeMap;

public class TimeBasedRule implements PriceRule {

    private static NavigableMap<Long, Double> daysPricingRulesMap;

    static {
        daysPricingRulesMap = new TreeMap<>();
        daysPricingRulesMap.put(Long.MIN_VALUE, 0d);
        daysPricingRulesMap.put(0L, 1.50);
        daysPricingRulesMap.put(3L, 1.20);
        daysPricingRulesMap.put(16L, 1.00);
        daysPricingRulesMap.put(31L, 0.80);

    }


    @Override
    public FlightTicket apply(SearchRequest searchRequest, Flight flight) {

        long daysPriorDeparture = calculateDaysBetween(searchRequest.getDepartureDate());
        BigDecimal price = calculatePrice(daysPriorDeparture, flight.getBasePrice());

        return new FlightTicket( flight.getOrigin(), flight.getDestination(), flight.getFlightCode(), flight.getAirline(), searchRequest.getNumAdults(),
                searchRequest.getNumChild(), searchRequest.getNumInfants(), price);
    }


    /**
     * Calculate days between departureDate and now
     * @param departureDate
     * @return
     */
    private long calculateDaysBetween(Date departureDate) {
        //Convert departureDate to LocalTime
        Instant instant = Instant.ofEpochMilli(departureDate.getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();
        
        return  ChronoUnit.DAYS.between(LocalDate.now(), localDate);
    }


    /**
     * Calculate price applying rule days to departure date
     * @param daysPriorDeparture
     * @param basePrice
     * @return
     */
    private BigDecimal calculatePrice(long daysPriorDeparture, BigDecimal basePrice) {

        basePrice = basePrice.multiply(new BigDecimal(daysPricingRulesMap.floorEntry(daysPriorDeparture).getValue()));

        return basePrice.setScale(2, RoundingMode.HALF_EVEN);
    }
}
