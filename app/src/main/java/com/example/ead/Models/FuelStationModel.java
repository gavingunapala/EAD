package com.example.ead.Models;

public class FuelStationModel {

    private String Id;
    private String StationName;
    private String Province;
    private String Location;
    private float RemaingPetrol;

    public FuelStationModel(String stationName , String location ,String id) {
        StationName = stationName;
        Location = location;
        Id = id;
    }

    public String getId() {
        return Id;
    }

    public String getStationName() {
        return StationName;
    }

    public String getProvince() {
        return Province;
    }

    public String getLocation() {
        return Location;
    }

    public float getRemaingPetrol() {
        return RemaingPetrol;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setStationName(String stationName) {
        StationName = stationName;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setRemaingPetrol(float remaingPetrol) {
        RemaingPetrol = remaingPetrol;
    }
}
