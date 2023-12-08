package com.code.angel.countrycitylist.service;

import static com.code.angel.countrycitylist.util.PredefinedEntities.CITY;
import static com.code.angel.countrycitylist.util.PredefinedEntities.CITY_DTO;
import static com.code.angel.countrycitylist.util.PredefinedEntities.COUNTRY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.code.angel.countrycitylist.dto.CityDto;
import com.code.angel.countrycitylist.mapper.CityMapper;
import com.code.angel.countrycitylist.model.City;
import com.code.angel.countrycitylist.repository.CityRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityMapper cityMapper;

    @InjectMocks
    private CityService cityService;

    @Test
    void testGetAll() {
        Pageable pageable = PageRequest.of(0, 10);
        List<City> cities = Collections.singletonList(CITY);

        when(cityRepository.findAll(pageable)).thenReturn(new PageImpl<>(cities));
        when(cityMapper.toDto(CITY)).thenReturn(CITY_DTO);

        Page<CityDto> result = cityService.getAll(pageable);

        assertEquals(1, result.getContent().size());

        verify(cityRepository).findAll(pageable);
        verify(cityMapper).toDto(CITY);
    }

    @Test
    void testGetUniqueCityNames() {
        List<String> uniqueCityNames = Collections.singletonList(COUNTRY.getName());

        when(cityRepository.findDistinctCityNames()).thenReturn(uniqueCityNames);

        List<String> result = cityService.getUniqueCityNames();

        assertEquals(1, result.size());

        verify(cityRepository).findDistinctCityNames();
    }

    @Test
    void testGetAllByCountryName() {
        String countryName = "Spain";
        List<City> cities = Collections.singletonList(CITY);

        when(cityRepository.findCitiesByCountryName(countryName)).thenReturn(cities);
        when(cityMapper.toDto(any())).thenReturn(CITY_DTO);

        List<CityDto> result = cityService.getAllByCountryName(countryName);

        assertEquals(1, result.size());

        verify(cityRepository).findCitiesByCountryName(COUNTRY.getName());
        verify(cityMapper).toDto(CITY);
    }

    @Test
    void testUpdateCity() {
        Long id = 1L;
        String updatedName = "NewCityName";

        when(cityRepository.findById(id)).thenReturn(Optional.of(CITY));
        when(cityMapper.toDto(CITY)).thenReturn(CITY_DTO);

        cityService.updateCity(id, updatedName);

        verify(cityRepository).findById(id);
        verify(cityMapper).toDto(CITY);

    }
}
