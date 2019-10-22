import repository.StationRepository;
import repository.StationRepositoryImpl;
import util.*;

public class Engine implements Runnable {

    private static final String COMMAND_END = "END";
    private static final String CHOOSE_OPTION = String.format("Choose Option:%n --input {filename}%n --output {filename}%n END");

    private ConsoleReader consoleReader;
    private ConsoleWriter consoleWriter;
    private FileReader fileReader;
    private FileWriter fileWriter;
    private StationRepository stationRepository;
    private CommandParser commandParser;

    public Engine() {
        this.consoleReader = new ConsoleReaderImpl();
        this.consoleWriter = new ConsoleWriterImpl();
        this.fileReader = new FileReaderImpl();
        this.fileWriter = new FileWriterImpl();
        this.stationRepository = new StationRepositoryImpl(GsonProvider.getGson());
        this.commandParser = new CommandParserImpl(fileReader, fileWriter, stationRepository);
    }

    @Override
    public void run() {
        while (true) {
            consoleWriter.writeLine(CHOOSE_OPTION);
            String inputLine = consoleReader.readLine();

            if (inputLine.equals(Engine.COMMAND_END)) {
                break;
            }

            String status = commandParser.execute(inputLine);

            consoleWriter.writeLine(status);
        }
    }
}
