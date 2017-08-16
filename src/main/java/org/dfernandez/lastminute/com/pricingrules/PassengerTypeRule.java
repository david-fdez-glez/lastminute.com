package org.dfernandez.lastminute.com.pricingrules;

import org.dfernandez.lastminute.com.dto.SearchRequest;
import org.dfernandez.lastminute.com.model.Flight;
import org.dfernandez.lastminute.com.model.FlightTicket;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PassengerTypeRule implements PriceRule {

    private static final double CHILD_PERCENTAGE = 0.67;

    private final PriceRule baseRule;
    
    public PassengerTypeRule(PriceRule priceRule) {
        this.baseRule = priceRule;
    }

    @Override
    public FlightTicket apply(SearchRequest searchRequest, Flight flight) {

        final FlightTicket ticketAfterBaseRule = baseRule.apply(searchRequest, flight);

        BigDecimal price = calculatePrice(searchRequest.getNumAdults(), searchRequest.getNumChild(), searchRequest.getNumInfants(), ticketAfterBaseRule.getTotalPrice(),
                flight.getAirline().getInfantPrice());

        ticketAfterBaseRule.setTotalPrice(price);
        return ticketAfterBaseRule;
    }


    /**
     * Calculate price applying passenger type
     * @param numAdults
     * @param numChild
     * @param numInfants
     * @param price
     * @param infantPrice
     * @return
     */
    private BigDecimal calculatePrice(int numAdults, int numChild, int numInfants, BigDecimal price, BigDecimal infantPrice) {

        BigDecimal totalPrice = BigDecimal.ZERO;

        if(numAdults > 0) {
            totalPrice = price.multiply(new BigDecimal(numAdults));
        }
        if(numChild > 0) {
            totalPrice = totalPrice.add( price.multiply( new BigDecimal(numChild).multiply(new BigDecimal(CHILD_PERCENTAGE))));
        }
        if(numInfants > 0) {
            totalPrice = totalPrice.add(infantPrice.multiply( new BigDecimal(numInfants)));
        }

        return totalPrice.setScale(2, RoundingMode.HALF_EVEN);

    }
}
