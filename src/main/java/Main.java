import com.google.gson.Gson;
import dto.StationImportDto;
import dto.StationExportDto;
import util.*;

import java.io.IOException;
import java.util.*;

public class Main {

    private static final String IMPORT_DATA_PATH =
            "C:\\Users\\Stefan\\IdeaProjects\\Java Spring MVC\\Json Processing\\src\\main\\resources\\inputDataJsonFile.json";

    private static final String EXPORT_DATA_PATH =
            "C:\\Users\\Stefan\\IdeaProjects\\Java Spring MVC\\Json Processing\\src\\main\\resources\\outputDataJson.json";

    public static void main(String[] args) {

        FileReader reader = new FileReaderImpl();
        FileWriter writer = new FileWriterImpl();
        Gson gson = GsonProvider.getGson();


        StationImportDto[] station;
        String text;
        try {
            text = reader.readFile(IMPORT_DATA_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            // or do something;
            return;
        }

        station = gson.fromJson(text, StationImportDto[].class);

        Map<String, List<Double>> allStations = new LinkedHashMap<>();

        Arrays.asList(station).forEach(stationImportDto -> {
            allStations.putIfAbsent(stationImportDto.getStationName(), new ArrayList<>());
            allStations.get(stationImportDto.getStationName()).add(stationImportDto.getPower());
        });

        List<StationExportDto> stationsExport = new ArrayList<>();
        for (String stationName : allStations.keySet()) {

            double average = allStations.get(stationName)
                    .stream()
                    .mapToDouble(x -> x)
                    .average()
                    .orElse(0.0);

            StationExportDto dto = new StationExportDto(stationName, average);

            stationsExport.add(dto);
        }

        String jsonText = gson.toJson(stationsExport);

        try {
            writer.write(EXPORT_DATA_PATH, jsonText);
        } catch (IOException e) {
            // Log print status... or something.
            e.printStackTrace();
        }
    }
}
