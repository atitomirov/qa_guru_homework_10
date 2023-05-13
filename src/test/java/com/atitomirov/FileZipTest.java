package com.atitomirov;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class FileZipTest {
    private ClassLoader cl = FileZipTest.class.getClassLoader();

    @DisplayName("Проверка zip архива")
    @Test
    void zipParseTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) {

                if (entry.getName().equals("Headset Sony MDR-10RBT.pdf")) {

                    PDF pdf = new PDF(zs);
                    assertEquals(2, pdf.numberOfPages);
                    assertTrue(pdf.text.startsWith("MDR-10RBT"));

                }

                if (entry.getName().equals("Auto.csv")) {

                    CSVReader csvReader = new CSVReader(new InputStreamReader(zs));
                    List<String[]> content = csvReader.readAll();
                    assertArrayEquals(new String[]{"Audi", "A7", "230"}, content.get(2));

                }

                if (entry.getName().equals("telefon-sprav.xlsx")) {

                    XLS xls = new XLS(zs);
                    assertTrue(xls.excel.getSheetAt(0).getRow(10).getCell(11)
                            .getStringCellValue().startsWith("Ремонтирует компы"));
                    assertTrue(xls.excel.getSheetAt(0).getRow(12).getCell(11)
                            .getStringCellValue().startsWith("Спец по отчетам"));

                }
            }
        }
    }
}
