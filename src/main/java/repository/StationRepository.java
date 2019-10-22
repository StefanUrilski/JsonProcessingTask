package repository;

public interface StationRepository {

    int addStationsFromJson(String stationsJson);

    String getAveragePower();

}
