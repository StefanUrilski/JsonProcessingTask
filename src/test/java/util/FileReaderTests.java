package util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class FileReaderTests {

    private FileReader fileReader;

    @Before
    public void init() {
        fileReader = new FileReaderImpl();
    }

    @Test
    public void readFile_CorrectFileName_CorrectResult() throws IOException {
        String expected = "[\r\n  {\r\n    \"stationName\":\"Zettahost\",\r\n    \"power\":-23.3333333\r\n  }\r\n]";

        String actual = fileReader.readFile("\\testing\\input.json");

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IOException.class)
    public void readFile_WrongOrNotExistFileName_Exception() throws IOException {
       fileReader.readFile("\\testing\\notExistedFile.json");
    }
}
