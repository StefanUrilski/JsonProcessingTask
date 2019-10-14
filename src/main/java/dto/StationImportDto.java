package dto;

import com.google.gson.annotations.Expose;

public class StationImportDto {

    @Expose
    private String stationName;

    @Expose
    private double power;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }
}
