package com.atitomirov;

import com.atitomirov.data.DataAuto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.InputStreamReader;

public class FileJsonTest {

    private ClassLoader cl = FileJsonTest.class.getClassLoader();

    @DisplayName("Проверка JSON файла")
    @Test
    void jsonParseTest() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        try (
                InputStream jsn = cl.getResourceAsStream("autoExample.json");
                InputStreamReader reader = new InputStreamReader(jsn);
        ) {

            DataAuto dataAuto = objectMapper.readValue(reader, DataAuto.class);

            Assertions.assertTrue(dataAuto.getTestDrive());
            Assertions.assertEquals(10005, dataAuto.getId());
            Assertions.assertEquals(4580, dataAuto.getSize().getLength());
            Assertions.assertEquals(1750, dataAuto.getSize().getWidth());
            Assertions.assertEquals(1640, dataAuto.getSize().getHeight());
            Assertions.assertEquals("Lada", dataAuto.getBrand());
            Assertions.assertEquals("Vesta", dataAuto.getModel());
            Assertions.assertEquals("Navigation", dataAuto.getOption().get(0));
            Assertions.assertEquals("Climatic", dataAuto.getOption().get(1));

        }
    }
}
