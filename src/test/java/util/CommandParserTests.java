package util;

import org.junit.Before;
import org.junit.Test;
import repository.StationRepository;
import repository.StationRepositoryImpl;

import java.io.IOException;

import static common.Constants.*;
import static org.junit.Assert.*;

public class CommandParserTests {

    private FileReader fileReader;
    private StationRepository stationRepository;
    private CommandParser commandParser;

    @Before
    public void init() {
        fileReader = new FileReaderImpl();
        stationRepository = new StationRepositoryImpl(GsonProvider.getGson());
        commandParser = new CommandParserImpl(fileReader, new FileWriterImpl(), stationRepository);
    }

    @Test
    public void execute_InputWithCorrectFileName_ShouldAddValuesToRepository() {
        commandParser.execute("--input input.json");

        // number of keys in file input.json
        int expected = 2;

        int actual = stationRepository.getData().size();

        assertEquals(expected, actual);
    }

    @Test
    public void execute_InputWithCorrectFileName_ShouldReturnProperMessage() {
        String actual = commandParser.execute("--input input.json");

        // number of Entities added from file input.json and message
        String expected = String.format(NUMBER_STATIONS_ADDED, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void execute_InputWithWrongFileName_ShouldReturnProperMessage() {
        String actual = commandParser.execute("--input WrongFileName.json");

        assertEquals(ERROR_MESSAGE, actual);
    }

    @Test
    public void execute_OutputWithEntities_ShouldWriteAveragePowerToFile() throws IOException {
        commandParser.execute("--input input.json");

        commandParser.execute("--output \\testing\\output.json");


        String expected = fileReader.readFile("\\testing\\output.json");

        String actual = "[\r\n  {\r\n    \"stationName\": \"TestSSID\",\r\n    \"averagePower\": -5.5\r\n  },\r\n  " +
                "{\r\n    \"stationName\": \"Zettahost\",\r\n    \"averagePower\": -23.333333333333332\r\n  }\r\n]";

        assertEquals(expected, actual);
    }

    @Test
    public void execute_OutputWithoutEntities_ShouldReturnProperMessage() throws IOException {
        String expected = commandParser.execute("--output output.json");

        assertEquals(expected, NO_ENTRIES_EXCEPTION_MESSAGE);
    }

    @Test
    public void execute_OutputWithEntities_ShouldReturnProperMessage() throws IOException {
        commandParser.execute("--input input.json");
        String expected = commandParser.execute("--output output.json");

        assertEquals(expected, SUCCESS_MESSAGE);
    }

    @Test
    public void execute_WithRandomText_ShouldReturnProperMessage() throws IOException {
        String expected = commandParser.execute("RandomText");

        assertEquals(expected, INVALID_COMMAND_MESSAGE);
    }
}
