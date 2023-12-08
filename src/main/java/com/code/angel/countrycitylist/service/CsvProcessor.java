package com.code.angel.countrycitylist.service;

import static com.code.angel.countrycitylist.util.Constants.CITY_INDEX;
import static com.code.angel.countrycitylist.util.Constants.COUNTRY_INDEX;
import static com.code.angel.countrycitylist.util.Constants.CSV_PATH;
import static com.code.angel.countrycitylist.util.Constants.FLAG_PATH;

import com.code.angel.countrycitylist.model.City;
import com.code.angel.countrycitylist.model.Country;
import com.code.angel.countrycitylist.repository.CityRepository;
import com.code.angel.countrycitylist.repository.CountryRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CsvProcessor implements ApplicationRunner {

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

    public void processCsvRecordsAndSaveEntities() {
        List<String> csvRecords = readCsvFileContent();
        for (String row : csvRecords) {
            String[] rowData = row.split(",");
            if (rowData.length >= 2) {
                var countryName = rowData[COUNTRY_INDEX];
                var cityName = rowData[CITY_INDEX];
                var country = countryRepository.findByName(countryName)
                        .orElseGet(() -> {
                            var newCountry = new Country();
                            newCountry.setName(countryName);
                            newCountry.setLogo(loadImageBytesFromResource(countryName));
                            return countryRepository.save(newCountry);
                        });
                var city = new City();
                city.setName(cityName);
                city.setCountry(country);
                cityRepository.save(city);
            }
        }
    }

    @SneakyThrows(IOException.class)
    private List<String> readCsvFileContent() {
        List<String> lines;
        List<String> stringList;
        lines = Files.readAllLines(Path.of(CSV_PATH));
        stringList = lines.stream().skip(1).collect(Collectors.toList());
        return stringList;
    }

    @SneakyThrows(IOException.class)
    private byte[] loadImageBytesFromResource(String imageName) {
        var resource = new ClassPathResource(FLAG_PATH + imageName + ".png");
        return Files.readAllBytes(resource.getFile().toPath());
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        processCsvRecordsAndSaveEntities();
    }

}
