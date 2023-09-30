package com.example._rent_apartment.service;


import com.example._rent_apartment.dto.ApartmentInfoDTO;

import java.util.List;

public interface GeolocService {

    public List<ApartmentInfoDTO> findApartmentByLoc(String latitude, String longitude);

    }
