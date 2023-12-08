package com.code.angel.countrycitylist.util;

import java.nio.file.FileSystems;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    public static final int COUNTRY_INDEX = 0;
    public static final int CITY_INDEX = 1;
    public static final String FILE_SEPARATOR = FileSystems.getDefault().getSeparator();
    public static final String CSV_PATH = "src" + FILE_SEPARATOR
            + "main" + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "cities.csv";
    public static final String FLAG_PATH = "static/flags/";
}
