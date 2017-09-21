package ru.lesson.service;

import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Рустем on 12.09.2017.
 */
public class SettingsTest {
    @Test
    public void value() throws Exception {
        final Settings settings = Settings.getInstance();
        Assert.assertEquals("jdbc:postgresql://127.0.0.1:5432/clinic",settings.getValue("jdbc.url"));
    }

}