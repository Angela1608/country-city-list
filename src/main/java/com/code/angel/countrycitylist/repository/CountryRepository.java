package com.code.angel.countrycitylist.repository;

import com.code.angel.countrycitylist.model.Country;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByName(String name);

    @Query("SELECT c.country FROM City c WHERE c.name = :cityName")
    Optional<Country> findByCityName(String cityName);

    Optional<Country> findByCitiesId(Long id);

}
