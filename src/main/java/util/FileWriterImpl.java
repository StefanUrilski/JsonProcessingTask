package util;

import java.io.IOException;

public class FileWriterImpl implements FileWriter {

    @Override
    public void write(String fileName, String text) throws IOException {
        String filePath = String.format("%s\\src\\main\\resources\\%s", System.getProperty("user.dir"), fileName);

        java.io.FileWriter writer = new java.io.FileWriter(filePath);

        String[] data = text.split(System.lineSeparator());

        for (String dataLine : data) {
            writer.write(dataLine);
        }

        writer.close();
    }
}
