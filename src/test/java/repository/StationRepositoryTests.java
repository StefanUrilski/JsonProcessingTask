package repository;

import entity.Station;
import org.junit.Before;
import org.junit.Test;
import util.GsonProvider;

import static org.junit.Assert.*;

public class StationRepositoryTests {
    private static final String STRING_JSON_SINGLE_OBJECT =
            "[\r\n  {\r\n    \"stationName\":\"Zettahost\",\r\n    \"power\":-23.3333333\r\n  }\r\n]";

    private static final String STRING_JSON_ANOTHER_OBJECT =
            "[\r\n  {\r\n    \"stationName\":\"Zettahost\",\r\n    \"power\":67.7733333\r\n  }\r\n]";


    private StationRepository stationRepository;

    @Before
    public void init() {
        stationRepository = new StationRepositoryImpl(GsonProvider.getGson());
    }

    @Test
    public void addStationsFromJson_CorrectStringJson_ShouldAddStation() {
        int actual = stationRepository.addStationsFromJson(STRING_JSON_SINGLE_OBJECT);

        assertEquals(1, actual);
    }

    @Test
    public void addStationsFromJson_CorrectStringJson_ShouldAddSameStation() {
        String stationName = "Zettahost";
        stationRepository.addStationsFromJson(STRING_JSON_SINGLE_OBJECT);

        Station station = stationRepository.getData().get(stationName).get(0);

        assertEquals(stationName, station.getName());
        assertEquals(-23.3, station.getPower(), 0.1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getAveragePower_NotAnyEntries_ShouldProduceException() {
        stationRepository.getAveragePower();
    }

    @Test
    public void getAveragePower_CorrectEntries_ShouldReturnCorrectValue() {
        String expected = "[\n  {\n    \"stationName\": \"Zettahost\",\n    \"averagePower\": 22.220000000000002\n  }\n]";

        stationRepository.addStationsFromJson(STRING_JSON_SINGLE_OBJECT);
        stationRepository.addStationsFromJson(STRING_JSON_ANOTHER_OBJECT);

        String actual = stationRepository.getAveragePower();

        assertEquals(expected, actual);
    }
}
