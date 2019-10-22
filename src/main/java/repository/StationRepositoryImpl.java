package repository;

import com.google.gson.Gson;
import dto.StationExportDto;
import dto.StationImportDto;
import entity.Station;

import java.util.*;

public class StationRepositoryImpl implements StationRepository {

    private Map<String, List<Station>> data;

    private Gson gson;

    public StationRepositoryImpl(Gson gson) {
        this.gson = gson;
        this.data = new HashMap<>();
    }

    @Override
    public String addStationsFromJson(String stationsJson) {
        StationImportDto[] stations = gson.fromJson(stationsJson, StationImportDto[].class);

        Arrays.stream(stations)
                .forEach(station -> {
                    Station currentStation = new Station(station.getStationName(), station.getPower());
                    data.putIfAbsent(station.getStationName(), new ArrayList<>());
                    data.get(station.getStationName()).add(currentStation);
                });

        return String.format("%d stations, successfully added.", stations.length);
    }

    @Override
    public List<StationExportDto> getAveragePower() {
        List<StationExportDto> stationsAverage = new ArrayList<>();

        data.forEach((key, value) -> {
            double average = value.stream()
                    .mapToDouble(Station::getAveragePower)
                    .average()
                    .orElse(0.0);

            StationExportDto stationDto = new StationExportDto(key, average);

            stationsAverage.add(stationDto);
        });

        return stationsAverage;
    }
}
