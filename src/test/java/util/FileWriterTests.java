package util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class FileWriterTests {

    private FileWriter fileWriter;
    private FileReader fileReader;

    @Before
    public void init() {
        fileWriter = new FileWriterImpl();
        fileReader = new FileReaderImpl();
    }

    @Test
    public void readFile_CorrectFileName_CorrectResult() throws IOException {
        String expected = "[\r\n  {\r\n    \"stationName\":\"Zettahost\",\r\n    \"power\":-23.3333333\r\n  }\r\n]";

        fileWriter.write("\\testing\\output.json", expected);

        String actual = fileReader.readFile("\\testing\\input.json");

        Assert.assertEquals(expected, actual);
    }
}
