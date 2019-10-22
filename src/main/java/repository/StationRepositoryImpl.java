package repository;

import com.google.gson.Gson;
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
        Station[] stations = gson.fromJson(stationsJson, Station[].class);

        Arrays.stream(stations)
                .forEach(station -> {
                    Station currentStation = new Station(station.getName(), station.getAveragePower());
                    data.putIfAbsent(station.getName(), new ArrayList<>());
                    data.get(station.getName()).add(currentStation);
                });

        return String.format("%d stations, successfully added.", stations.length);
    }

    @Override
    public List<Station> getAveragePower() {
        return null;
    }
}
