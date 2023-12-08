package com.code.angel.countrycitylist.mapper;

import com.code.angel.countrycitylist.dto.CityDto;
import com.code.angel.countrycitylist.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CityMapper {

    @Mapping(target = "id", source = "city.id")
    @Mapping(target = "countryDto.id", source = "city.country.id")
    @Mapping(target = "countryDto.name", source = "city.country.name")
    @Mapping(target = "countryDto.logo", source = "city.country.logo")
    CityDto toDto(City city);

}
