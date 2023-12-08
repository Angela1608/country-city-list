package com.code.angel.countrycitylist.service;

import static com.code.angel.countrycitylist.util.PredefinedEntities.CITY;
import static com.code.angel.countrycitylist.util.PredefinedEntities.COUNTRY;
import static com.code.angel.countrycitylist.util.PredefinedEntities.COUNTRY_DTO;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.code.angel.countrycitylist.mapper.CountryMapper;
import com.code.angel.countrycitylist.repository.CountryRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

@ExtendWith(MockitoExtension.class)
class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryMapper countryMapper;

    @InjectMocks
    private CountryService countryService;

    @Test
    void getByCityName() {
        when(countryRepository.findByCityName(CITY.getName()))
                .thenReturn(Optional.ofNullable(COUNTRY));
        when(countryMapper.toDto(COUNTRY)).thenReturn(COUNTRY_DTO);

        countryService.getByCityName(CITY.getName());

        verify(countryRepository).findByCityName(CITY.getName());
        verify(countryMapper).toDto(COUNTRY);
    }

    @Test
    void updateCountryLogo() {
        when(countryRepository.findByCitiesId(CITY.getId()))
                .thenReturn(Optional.ofNullable(COUNTRY));

        MockMultipartFile logo = new MockMultipartFile(
                "logo", "logo.png", "image/png", "testdata".getBytes());

        countryService.updateCountryLogo(CITY.getId(), logo);

        verify(countryRepository).findByCitiesId(CITY.getId());
    }

}
