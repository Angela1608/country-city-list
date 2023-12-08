package com.code.angel.countrycitylist.util;

import com.code.angel.countrycitylist.dto.CityDto;
import com.code.angel.countrycitylist.dto.CountryDto;
import com.code.angel.countrycitylist.model.City;
import com.code.angel.countrycitylist.model.Country;
import java.util.ArrayList;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PredefinedEntities {

    public static final City CITY = City.builder()
            .id(1L)
            .country(new Country())
            .name("Madrid")
            .build();

    public static final Country COUNTRY = Country.builder()
            .id(1L)
            .cities(new ArrayList<>())
            .name("Spain")
            .logo(new byte[]{})
            .build();

    public static final CityDto CITY_DTO = CityDto.builder()
            .id(1L)
            .name("Madrid")
            .build();

    public static final CountryDto COUNTRY_DTO = CountryDto.builder()
            .id(1L)
            .name("Spain")
            .logo(new byte[]{})
            .build();

}
