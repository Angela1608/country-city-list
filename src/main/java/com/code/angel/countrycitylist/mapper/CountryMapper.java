package com.code.angel.countrycitylist.mapper;

import com.code.angel.countrycitylist.dto.CountryDto;
import com.code.angel.countrycitylist.model.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDto toDto(Country country);
}
