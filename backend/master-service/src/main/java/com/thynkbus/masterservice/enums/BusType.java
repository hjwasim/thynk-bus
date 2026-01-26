package com.thynkbus.masterservice.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
// effect only in output, NOT in DB
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BusType {

    // if sleeper comes, then bus must have upper deck

    TWO_PLUS_TWO_NON_AC_SEATER("2+2 Non A/C Seater", false),
    TWO_PLUS_TWO_AC_SEATER("2+2 A/C Seater", false),

    TWO_PLUS_ONE_SLEEPER("2+1 A/C Sleeper", true),
    TWO_PLUS_ONE_NON_AC_SLEEPER("2+1 Non A/C Sleeper", true),

    TWO_PLUS_ONE_SLEEPER_SEATER("2+1 Sleeper/Seater", true),
    TWO_PLUS_ONE_NON_AC_SLEEPER_SEATER("2+1 Non A/C Sleeper/Seater", true);


    private final String name;
    private final boolean upperDeck;


    BusType(String s, boolean upperDeck) {
        this.name = s;
        this.upperDeck = upperDeck;
    }

    public String code() {
        return toString();
    }
}
