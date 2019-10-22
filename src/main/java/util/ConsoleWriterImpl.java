package util;

public class ConsoleWriterImpl implements ConsoleWriter {

    @Override
    public void writeLine(String message) {
        System.out.println(message);
    }
}
