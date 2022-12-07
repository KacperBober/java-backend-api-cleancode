package com.kainos.ea.model;

public class Band {
    private int band_level_id;
    private int band_level;
    private String band_name;


    public Band(){

    }
    public Band(int band_level, String band_name) {
        this.band_level = band_level;
        this.band_name = band_name;
    }

    public Band(int band_level_id, int band_level, String band_name) {
        this.band_level_id = band_level_id;
        this.band_level = band_level;
        this.band_name = band_name;
    }

    public int getBand_level_id() {
        return band_level_id;
    }

    public void setBand_level_id(int band_level_id) {
        this.band_level_id = band_level_id;
    }

    public int getBand_level() {
        return band_level;
    }

    public void setBand_level(int band_level) {
        this.band_level = band_level;
    }

    public String getBand_name() {
        return band_name;
    }

    public void setBand_name(String band_name) {
        this.band_name = band_name;
    }
}
