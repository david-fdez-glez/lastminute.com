package org.dfernandez.lastminute.com.service;

import org.dfernandez.lastminute.com.dto.SearchRequest;
import org.dfernandez.lastminute.com.dto.SearchResponse;
import org.dfernandez.lastminute.com.functional.FlightPredicates;
import org.dfernandez.lastminute.com.model.Flight;
import org.dfernandez.lastminute.com.model.FlightTicket;
import org.dfernandez.lastminute.com.pricingrules.PriceRule;
import org.dfernandez.lastminute.com.util.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(FlightPredicates.class)
public class SearchEngineTest {

    private PriceRule priceRule;

    private Flight flightA;
    private Flight flightB;
    private Flight flightC;
    private Flight flightD;

    private List<Flight> flightData;
    private SearchRequest request;

    private FlightTicket flightTicketA;
    private FlightTicket flightTicketB;
    private FlightTicket flightTicketC;
    private FlightTicket flightTicketD;

    private SearchEngine searchEngine;
    
    private Predicate<Flight> predicateTrue;

    @Before
    public void setUp() {
        priceRule = mock(PriceRule.class);
        flightA = mock(Flight.class);
        flightB = mock(Flight.class);
        flightC = mock(Flight.class);
        flightD = mock(Flight.class);
        flightData = new ArrayList<>();
        flightData.addAll(Arrays.asList(flightA, flightB, flightC, flightD));
        flightTicketA = mock(FlightTicket.class);
        flightTicketB = mock(FlightTicket.class);
        flightTicketC = mock(FlightTicket.class);
        flightTicketD = mock(FlightTicket.class);

        searchEngine = new SearchEngineImpl(priceRule, flightData);

        PowerMockito.mockStatic(FlightPredicates.class);
        predicateTrue = ( p -> true);

        //when predicates
        when(FlightPredicates.matchRoute(any(String.class), any(String.class))).thenReturn(predicateTrue);
    }

    @Test
    public void testOneAdultMoreThirtyOneDaysAmsToFra() {
        //* 1 adult, 31 days to the departure date, flying AMS -> FRA
        request = new SearchRequest("AMS", "FRA", TestUtils.getDatePlusDays(31), 1, 0 , 0);

        //when predicates
        when(FlightPredicates.filterFlights(flightData, predicateTrue)).thenReturn(Arrays.asList(flightB, flightC, flightD));
        //when flightTickets
        when(flightTicketB.getFlightCode()).thenReturn("TK2372");
        when(flightTicketB.getTotalPrice()).thenReturn(TestUtils.getBigDecimalScale(157.6));
        when(flightTicketC.getFlightCode()).thenReturn("TK2659");
        when(flightTicketC.getTotalPrice()).thenReturn(TestUtils.getBigDecimalScale(198.4));
        when(flightTicketD.getFlightCode()).thenReturn("LH5909");
        when(flightTicketD.getTotalPrice()).thenReturn(TestUtils.getBigDecimalScale(90.4));
        //when rules
        when(priceRule.apply(request, flightB)).thenReturn(flightTicketB);
        when(priceRule.apply(request, flightC)).thenReturn(flightTicketC);
        when(priceRule.apply(request, flightD)).thenReturn(flightTicketD);

        List<SearchResponse> response = searchEngine.search(request);
        List<SearchResponse> expectedResponse = new ArrayList<>();
        expectedResponse.add(new SearchResponse("TK2372",TestUtils.getBigDecimalScale(157.6)));
        expectedResponse.add(new SearchResponse("TK2659",TestUtils.getBigDecimalScale(198.4)));
        expectedResponse.add(new SearchResponse("LH5909",TestUtils.getBigDecimalScale(90.4)));

        assertThat(response, hasSize( 3 ));
        assertThat(response, equalTo(expectedResponse));

        verify(priceRule, times(3)).apply(eq(request), any(Flight.class));
    }

    @Test
    public void testTwoAdultsOneChildOneInfantFifteenDaysLhrToIst() {
        //* 2 adults, 1 child, 1 infant, 15 days to the departure date, flying LHR -> IST
        request = new SearchRequest("LHR", "IST", TestUtils.getDatePlusDays(15), 2, 1 , 1);

       
        when(FlightPredicates.filterFlights(flightData, predicateTrue)).thenReturn(Arrays.asList(flightA, flightB));
        //when flightTickets
        when(flightTicketA.getFlightCode()).thenReturn("TK8891");
        when(flightTicketA.getTotalPrice()).thenReturn(TestUtils.getBigDecimalScale(806));
        when(flightTicketB.getFlightCode()).thenReturn("LH1085");
        when(flightTicketB.getTotalPrice()).thenReturn(TestUtils.getBigDecimalScale(481.19));
       
        //when rules
        when(priceRule.apply(request, flightA)).thenReturn(flightTicketA);
        when(priceRule.apply(request, flightB)).thenReturn(flightTicketB);

        List<SearchResponse> response = searchEngine.search(request);
        List<SearchResponse> expectedResponse = new ArrayList<>();
        expectedResponse.add(new SearchResponse("TK8891",TestUtils.getBigDecimalScale(806)));
        expectedResponse.add(new SearchResponse("LH1085",TestUtils.getBigDecimalScale(481.19)));

        assertThat(response, hasSize( 2 ));
        assertThat(response, equalTo(expectedResponse));

        verify(priceRule, times(2)).apply(eq(request), any(Flight.class));
    }

    @Test
    public void testOneAdultTwoChildrenTwoDaysBcnToMad() {
        //* 1 adult, 2 children, 2 days to the departure date, flying BCN -> MAD
        request = new SearchRequest("BCN", "MAD", TestUtils.getDatePlusDays(2), 1, 2 , 0);


        when(FlightPredicates.filterFlights(flightData, predicateTrue)).thenReturn(Arrays.asList(flightC, flightD));
        //when flightTickets
        when(flightTicketC.getFlightCode()).thenReturn("IB2171");
        when(flightTicketC.getTotalPrice()).thenReturn(TestUtils.getBigDecimalScale(909.09));
        when(flightTicketD.getFlightCode()).thenReturn("LH5496");
        when(flightTicketD.getTotalPrice()).thenReturn(TestUtils.getBigDecimalScale(1028.43));

        //when rules
        when(priceRule.apply(request, flightC)).thenReturn(flightTicketC);
        when(priceRule.apply(request, flightD)).thenReturn(flightTicketD);

        List<SearchResponse> response = searchEngine.search(request);
        List<SearchResponse> expectedResponse = new ArrayList<>();
        expectedResponse.add(new SearchResponse("IB2171",TestUtils.getBigDecimalScale(909.09)));
        expectedResponse.add(new SearchResponse("LH5496",TestUtils.getBigDecimalScale(1028.43)));

        assertThat(response, hasSize( 2 ));
        assertThat(response, equalTo(expectedResponse));

        verify(priceRule, times(2)).apply(eq(request), any(Flight.class));
    }

    @Test
    public void testNoFlightsAvailable() {
        //* 1 adult, 1 child, 1 infant 42 days to the departure date, flying CDG -> FRA
        request = new SearchRequest("CDG", "FRA", TestUtils.getDatePlusDays(42), 1, 1 , 1);


        when(FlightPredicates.filterFlights(flightData, predicateTrue)).thenReturn(Collections.emptyList());

        List<SearchResponse> response = searchEngine.search(request);
        List<SearchResponse> expectedResponse = new ArrayList<>();

        assertThat(response, hasSize( 0 ));
        assertThat(response, equalTo(expectedResponse));

        verify(priceRule, never()).apply(eq(request), any(Flight.class));
    }


}
