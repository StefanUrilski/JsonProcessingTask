package dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StationExportDto {

    @Expose
    private String stationName;

    @Expose
    @SerializedName("averagePower")
    private double power;

    public StationExportDto(String stationName, double power) {
        this.stationName = stationName;
        this.power = power;
    }

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
