package repository;

import dto.StationExportDto;

import java.util.List;

public interface StationRepository {

    String addStationsFromJson(String stationsJson);

    List<StationExportDto> getAveragePower();

}
