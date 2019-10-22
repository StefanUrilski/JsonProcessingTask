package repository;

import entity.Station;

import java.util.List;

public interface StationRepository {

    String addStationsFromJson(String stationsJson);

    List<Station> getAveragePower();

}
