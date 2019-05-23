package com.interpreter;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Configuration {

    public static Configuration getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final Configuration INSTANCE = new Configuration();
    }

    private Properties properties;

    private Configuration() {
        properties = new Properties();

        try {
            String path = "/config.properties";

            FileInputStream fileInputStream = new FileInputStream(new File(getClass().getResource(path).getFile()));
            properties.load(fileInputStream);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        String value = "";
        if (properties.containsKey(key)) {
            value = (String) properties.get(key);
        }
        return value;
    }
}
