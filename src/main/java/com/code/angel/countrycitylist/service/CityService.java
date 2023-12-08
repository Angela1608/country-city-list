package com.code.angel.countrycitylist.service;

import static java.util.Optional.ofNullable;

import com.code.angel.countrycitylist.dto.CityDto;
import com.code.angel.countrycitylist.exception.EntityNotFoundException;
import com.code.angel.countrycitylist.mapper.CityMapper;
import com.code.angel.countrycitylist.model.City;
import com.code.angel.countrycitylist.repository.CityRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public Page<CityDto> getAll(Pageable pageable) {
        return cityRepository.findAll(pageable)
                .map(cityMapper::toDto);
    }

    public List<String> getUniqueCityNames() {
        return cityRepository.findDistinctCityNames();
    }

    public List<CityDto> getAllByCountryName(String countryName) {
        return cityRepository.findCitiesByCountryName(countryName).stream()
                .map(cityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CityDto updateCity(Long id, String name) {
        var city = getCityById(id);
        ofNullable(name).ifPresent(city::setName);
        return cityMapper.toDto(city);
    }

    private City getCityById(Long id) {
        String msg = String.format("Can't find city by id '%s'", id);
        return cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(msg));
    }

}
