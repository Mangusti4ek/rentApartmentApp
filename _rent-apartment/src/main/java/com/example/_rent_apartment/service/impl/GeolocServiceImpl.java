package com.example._rent_apartment.service.impl;

import com.example._rent_apartment.application_exeption.NotFoundAvailableApartmentInLocationException;
import com.example._rent_apartment.application_exeption.NotFoundConfigIntegrationException;
import com.example._rent_apartment.dto.ApartmentInfoDTO;
import com.example._rent_apartment.dto.GeoCoderResponseDTO;
import com.example._rent_apartment.mapper.RentMapper;
import com.example._rent_apartment.model.entity.IntegrationInfoEntity;
import com.example._rent_apartment.repository.AddressInfoRepository;
import com.example._rent_apartment.repository.IntegrationInfoRepository;
import com.example._rent_apartment.service.GeolocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;
import static com.example._rent_apartment.constant.ApplicationConstant.GEO_LOC;
import static com.example._rent_apartment.service.impl.Base64Service.decoder;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class GeolocServiceImpl implements GeolocService {

    private final AddressInfoRepository addressInfoRepository;
    private final IntegrationInfoRepository integrationInfoRepository;
    private final RentMapper rentMapper;

    @Override
    public List<ApartmentInfoDTO> findApartmentByLoc(String latitude, String longitude) {
        RestTemplate restTemplate = new RestTemplate();
        GeoCoderResponseDTO body = restTemplate.exchange(prepareRequestPath(latitude, longitude),
                HttpMethod.GET,
                new HttpEntity<>(null),
                GeoCoderResponseDTO.class).getBody();
      /*  String city = searchCity(body);*/
        return prepareGeocoderResponse(body);
    }

    private List<ApartmentInfoDTO> getApartment(String value) {
        return addressInfoRepository.getAddressInfoEntitiesByCity(value).stream()
                .map(x -> rentMapper.prepareApartmentResponse(x,x.getApartmentInfo())).collect(Collectors.toList());
    }

    private String prepareRequestPath(String latitude, String longitude){
        IntegrationInfoEntity pathInfo = integrationInfoRepository.findById(GEO_LOC)
                .orElseThrow(() -> new NotFoundConfigIntegrationException());

        return String.format(pathInfo.getId() + pathInfo.getPathValue(),latitude,longitude,decoder(pathInfo.getApiKey()));

    }

    private List<ApartmentInfoDTO> prepareGeocoderResponse(GeoCoderResponseDTO body){
        String city = body.getResultsObjects().get(0).getComponentsDTO().getCity();
        String town = body.getResultsObjects().get(0).getComponentsDTO().getTown();
        if(isNull(city)) {
            return getApartment(city);
        } else if(isNull(town)){
            return getApartment(town);
        }
        throw new NotFoundAvailableApartmentInLocationException();
    }

/*    private String searchCity(String body) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(body);
        String city = jsonNode.get("results").get(0).get("components").get("city").asText();
        if(isNull(city)){
            String town = jsonNode.get("results").get(0).get("components").get("town").asText();
            return town;
        }
        return city;
        }  catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }*/


}
