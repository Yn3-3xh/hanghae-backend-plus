package org.example.junitinaction3.chapter13.passengers;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Locale;

@Getter
public class Passenger {

    private String identifier;
    private String name;
    private String countryCode;

    public Passenger(String identifier, String name, String countryCode) {
        if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new RuntimeException("국가 코드가 적절하지 않습니다.");
        }
        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "Passenger " + getName()
                + " with identifier: " + getIdentifier()
                + " from " + getCountryCode();
    }
}
