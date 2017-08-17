package org.dfernandez.lastminute.com.pricingrules;

import org.dfernandez.lastminute.com.dto.SearchRequest;
import org.dfernandez.lastminute.com.model.Airline;
import org.dfernandez.lastminute.com.model.Flight;
import org.dfernandez.lastminute.com.model.FlightTicket;
import org.dfernandez.lastminute.com.util.TestUtils;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class PricingRulesTest {

    private PriceRule timeBasedRule;

    private PriceRule passengerTypeRule;

    private SearchRequest request;

    private Flight flightAmsFra;

    private Flight flightLhrIst;

    private Flight flightBcnMad;

    private Flight flightLhrCdg;


    private FlightTicket flightTicket;

    private Airline turkish;

    private Airline lufthansa;

    private Airline iberia;

    @Before
    public void setUp() {
        timeBasedRule = new TimeBasedRule();
        passengerTypeRule = new PassengerTypeRule(timeBasedRule);
        turkish = new Airline("TK", "Turkish Airlines", new BigDecimal(5));
        lufthansa = new Airline("LH", "Lufthansa", new BigDecimal(7));
        iberia = new Airline("IB", "Iberia", new BigDecimal(10));

        flightAmsFra = new Flight(flight -> {
            flight.setOrigin("AMS");
            flight.setDestination("FRA");
            flight.setFlightCode("TK2372");
            flight.setAirline(turkish);
            flight.setBasePrice(new BigDecimal(197));
        });
        flightLhrIst = new Flight(flight -> {
            flight.setOrigin("LHR");
            flight.setDestination("IST");
            flight.setFlightCode("LH1085");
            flight.setAirline(lufthansa);
            flight.setBasePrice(new BigDecimal(148));
        });
        flightBcnMad = new Flight(flight -> {
            flight.setOrigin("BCN");
            flight.setDestination("MAD");
            flight.setFlightCode("IB2171");
            flight.setAirline(iberia);
            flight.setBasePrice(new BigDecimal(259));
        });
        flightLhrCdg = new Flight(flight -> {
            flight.setOrigin("LHR");
            flight.setDestination("CDG");
            flight.setFlightCode("IB2771");
            flight.setAirline(iberia);
            flight.setBasePrice(new BigDecimal(289));
        });
    }


    @Test
    public void timeBasedRuleThirtyOneDays() {
        //* 1 adult, 31 days to the departure date, flying AMS -> FRA TK2372
        request = new SearchRequest("AMS", "FRA", TestUtils.getDatePlusDays(31),1, 0, 0);
        flightTicket = timeBasedRule.apply(request, flightAmsFra);
        assertThat(TestUtils.getBigDecimalScale(157.6), equalTo(flightTicket.getTotalPrice()));
    }

    @Test
    public void passengerTypeRuleFifteenDays() {
        //* 2 adults, 1 child, 1 infant, 15 days to the departure date, flying LHR -> IST LH1085
        request = new SearchRequest("LHR", "IST", TestUtils.getDatePlusDays(15),2, 1, 1);
        flightTicket = passengerTypeRule.apply(request, flightLhrIst);
        assertThat(TestUtils.getBigDecimalScale(481.19), equalTo(flightTicket.getTotalPrice()));
    }

    @Test
    public void passengerTypeRuleTwoDays() {
        //1 adult, 2 children, 2 days to the departure date, flying BCN -> MAD  IB2171
        request = new SearchRequest("BCN", "MAD", TestUtils.getDatePlusDays(2),1, 2, 0);
        flightTicket = passengerTypeRule.apply(request, flightBcnMad);
        assertThat(TestUtils.getBigDecimalScale(909.09), equalTo(flightTicket.getTotalPrice()));
    }

    @Test
    public void passengerTypeRuleThirtyDays() {
        //1 adult, 0 child, 2 infants 30 days to the departure date, flying BCN -> MAD  IB2171
        request = new SearchRequest("LHR", "CDG", TestUtils.getDatePlusDays(30),1, 0, 2);
        flightTicket = passengerTypeRule.apply(request, flightLhrCdg);
        assertThat(TestUtils.getBigDecimalScale(289 + 20), equalTo(flightTicket.getTotalPrice()));
    }
}
