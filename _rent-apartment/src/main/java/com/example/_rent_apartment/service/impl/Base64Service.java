package com.example._rent_apartment.service.impl;

import java.util.Base64;

public class Base64Service {

    public static String encode(String value) {

        Base64.Encoder encoder = Base64.getEncoder();
        String securityValue = encoder.encodeToString(value.getBytes());
        return securityValue;

    }

    ;

    public static String decoder(String value) {

        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(value));
//        return String.valueOf(decoder.decode(value));

    }

}
