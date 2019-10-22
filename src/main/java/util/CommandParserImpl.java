package util;

import repository.StationRepository;

import java.io.IOException;

import static common.Constants.*;

public class CommandParserImpl implements CommandParser {

    private FileReader fileReader;
    private FileWriter fileWriter;
    private StationRepository stationRepository;

    public CommandParserImpl(FileReader fileReader, FileWriter fileWriter, StationRepository stationRepository) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
        this.stationRepository = stationRepository;
    }

    @Override
    public String execute(String command) {
        String[] tokens = command.split("\\s+");

        String cmd = tokens[0].replaceAll("-", "");
        String fileName = tokens.length == 2 ? tokens[1] : "";
        String executionResult;

        switch (cmd) {
            case "input":
                String fileContent;
                try {
                    fileContent = fileReader.readFile(fileName);
                    int addedStations = stationRepository.addStationsFromJson(fileContent);
                    executionResult = String.format(NUMBER_STATIONS_ADDED, addedStations);
                } catch (IOException e) {
                    e.printStackTrace();
                    executionResult = ERROR_MESSAGE;
                }
                break;
            case "output":
                String jsonOutput;

                try {
                    jsonOutput = stationRepository.getAveragePower();
                } catch (UnsupportedOperationException ex) {
                    executionResult = NO_ENTRIES_EXCEPTION_MESSAGE;
                    break;
                }

                try {
                    fileWriter.write(fileName, jsonOutput);
                    executionResult = SUCCESS_MESSAGE;
                } catch (IOException e) {
                    e.printStackTrace();
                    executionResult = ERROR_MESSAGE;
                }
                break;
            default:
                executionResult = INVALID_COMMAND_MESSAGE;
                break;
        }

        return executionResult;
    }
}
