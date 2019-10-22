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
    public int addStationsFromJson(String stationsJson) {
        StationImportDto[] stations = gson.fromJson(stationsJson, StationImportDto[].class);

        Arrays.stream(stations)
                .forEach(station -> {
                    Station currentStation = new Station(station.getStationName(), station.getPower());
                    data.putIfAbsent(station.getStationName(), new ArrayList<>());
                    data.get(station.getStationName()).add(currentStation);
                });

        return stations.length;
    }

    @Override
    public String getAveragePower() {
        List<StationExportDto> stationsAverage = new ArrayList<>();

        if (data.isEmpty()) throw new UnsupportedOperationException();

        data.forEach((key, value) -> {
            double average = value.stream()
                    .mapToDouble(Station::getPower)
                    .average()
                    .orElse(0.0);

            StationExportDto stationDto = new StationExportDto(key, average);

            stationsAverage.add(stationDto);
        });

        return gson.toJson(stationsAverage);
    }
}
