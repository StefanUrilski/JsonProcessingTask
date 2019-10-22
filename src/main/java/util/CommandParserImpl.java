package util;

import repository.StationRepository;

import java.io.IOException;

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
        String fileName = tokens[1];
        String executionResult;

        switch (cmd) {
            case "input":
                String fileContent = "";
                try {
                    fileContent = fileReader.readFile(fileName);
                    executionResult = stationRepository.addStationsFromJson(fileContent);
                } catch (IOException e) {
                    e.printStackTrace();
                    executionResult = "Error. Something goes wrong.";
                }
                break;
            case "output":
                String jsonOutput = stationRepository.getAveragePower();

                try {
                    fileWriter.write(fileName, jsonOutput);
                    executionResult = "Successfully saved to file.";
                } catch (IOException e) {
                    e.printStackTrace();
                    executionResult = "Error. Something goes wrong.";
                }
                break;
            default:
                executionResult = "Not a valid command! Please try again.";
                break;
        }

        return executionResult;
    }
}
