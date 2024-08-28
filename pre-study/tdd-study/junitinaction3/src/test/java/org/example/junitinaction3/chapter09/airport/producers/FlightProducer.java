package org.example.junitinaction3.chapter09.airport.producers;

import org.example.junitinaction3.chapter09.airport.Flight;
import org.example.junitinaction3.chapter09.airport.FlightBuilderUtil;

import javax.enterprise.inject.Produces;
import java.io.IOException;

public class FlightProducer {

    @Produces
    public Flight createFlight() throws IOException {
        return FlightBuilderUtil.buildFlightFromCsv();
    }
}