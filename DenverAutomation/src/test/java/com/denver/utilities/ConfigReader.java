package com.denver.utilities;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {
	
	//ConfigReader.java — reads configuration file//
    private static final Properties properties = new Properties();

    static {
        try (InputStream input =
                 ConfigReader.class.getClassLoader()
                     .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new IllegalStateException(
                    "config.properties not found in test resources");
            }

            properties.load(input);

        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private ConfigReader() {
    }

    public static String get(String key) {
        String value = properties.getProperty(key);

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                "Missing configuration key: " + key);
        }

        return value.trim();
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }
}
