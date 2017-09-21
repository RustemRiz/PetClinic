package ru.lesson.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Рустем on 10.09.2017.
 */
public class Settings {
    private static final Settings INSTANCE= new Settings();

    private final Properties properties = new Properties();

    private  Settings() {
        try {
            properties.load(new FileInputStream(
                    this.getClass().getClassLoader().getResource("rustem.properties").getFile()));
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

        public static Settings getInstance(){
            return INSTANCE;
        }

        public String getValue(String key){
            return properties.getProperty(key);
        }

}

