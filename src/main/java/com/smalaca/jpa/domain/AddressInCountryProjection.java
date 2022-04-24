package com.smalaca.jpa.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
@Getter
public class AddressInCountryProjection {
    private final String street;
    private final String postalCode;
    private final String city;
}
