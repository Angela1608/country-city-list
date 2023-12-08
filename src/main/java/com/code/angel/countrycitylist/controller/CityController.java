package com.code.angel.countrycitylist.controller;

import com.code.angel.countrycitylist.dto.CityDto;
import com.code.angel.countrycitylist.service.CityService;
import com.code.angel.countrycitylist.service.CountryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;
    private final CountryService countryService;

    @GetMapping
    public Page<CityDto> getAll(Pageable pageable) {
        return cityService.getAll(pageable);
    }

    @GetMapping("/unique-names")
    public List<String> getUniqueCityNames() {
        return cityService.getUniqueCityNames();
    }

    @GetMapping("/country-name")
    public List<CityDto> getAllByCountryName(@RequestParam String countryName) {
        return cityService.getAllByCountryName(countryName);
    }

    @PatchMapping ("/{id}")
    @PreAuthorize(value = "hasRole('EDITOR')")
    public CityDto editCity(@PathVariable("id") Long id,
                            @RequestParam("logo") MultipartFile logo,
                            @RequestParam("name") String name) {
        countryService.updateCountryLogo(id,logo);
        return cityService.updateCity(id, name);
    }

}
