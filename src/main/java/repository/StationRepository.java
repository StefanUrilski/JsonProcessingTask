package repository;

import entity.Station;

import java.util.List;
import java.util.Map;

public interface StationRepository {

    int addStationsFromJson(String stationsJson);

    String getAveragePower();

    Map<String, List<Station>> getData();

}
