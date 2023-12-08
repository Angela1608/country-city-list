package com.code.angel.countrycitylist.controller;

import com.code.angel.countrycitylist.dto.CountryDto;
import com.code.angel.countrycitylist.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/city-name")
    public CountryDto getByCityName(@RequestParam String cityName) {
        return countryService.getByCityName(cityName);
    }
}
