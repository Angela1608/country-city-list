package com.code.angel.countrycitylist.repository;

import com.code.angel.countrycitylist.model.City;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Page<City> findAll(Pageable pageable);

    @Query("SELECT DISTINCT c.name FROM City c")
    List<String> findDistinctCityNames();

    List<City> findCitiesByCountryName(String countryName);

}
