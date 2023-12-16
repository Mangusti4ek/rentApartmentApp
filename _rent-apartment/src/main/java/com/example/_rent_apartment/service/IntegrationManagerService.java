package com.example._rent_apartment.service;


import com.example._rent_apartment.dto.ApartmentInfoDTO;

import java.util.List;

public interface IntegrationManagerService {

    public List<ApartmentInfoDTO> findApartmentByLoc(String latitude, String longitude);

    public void prepareProductDiscount(Long id);
}
