package com.atitomirov.data;

import java.util.List;

public class DataAuto {

    private Integer id;
    private String brand;
    private String model;
    private Size size;
    private List<String> option;
    private Boolean testDrive;

    public Integer getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Size getSize() {
        return size;
    }

    public List<String> getOption() {
        return option;
    }

    public Boolean getTestDrive() {
        return testDrive;
    }

    public static class Size {
        Integer length, width, height;

        public Integer getLength() {
            return length;
        }

        public Integer getWidth() {
            return width;
        }

        public Integer getHeight() {
            return height;
        }
    }
}
