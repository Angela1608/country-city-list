package com.code.angel.countrycitylist.service;

import com.code.angel.countrycitylist.dto.CountryDto;
import com.code.angel.countrycitylist.exception.EntityNotFoundException;
import com.code.angel.countrycitylist.mapper.CountryMapper;
import com.code.angel.countrycitylist.model.Country;
import com.code.angel.countrycitylist.repository.CountryRepository;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public CountryDto getByCityName(String cityName) {
        String msg = String.format("Can't find city by cityName '%s'", cityName);
        return countryRepository.findByCityName(cityName)
                .map(countryMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(msg));
    }

    public void updateCountryLogo(Long cityId, MultipartFile logo) {
        var country = getCountryByCityId(cityId);
        Optional.ofNullable(logo).ifPresent(multipartFile -> {
            try {
                if (multipartFile.isEmpty()) {
                    throw new IllegalArgumentException("Logo file is empty");
                }
                if (!"image/png".equals(multipartFile.getContentType())) {
                    throw new IllegalArgumentException("Invalid file format. Expected image/png");
                }
                country.setLogo(multipartFile.getBytes());
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to read logo file", e);
            }
        });
    }

    private Country getCountryByCityId(Long cityId) {
        String msg = String.format("Can't find country by cityId '%s'", cityId);
        return countryRepository.findByCitiesId(cityId).orElseThrow(
                () -> new EntityNotFoundException(msg));
    }

}
