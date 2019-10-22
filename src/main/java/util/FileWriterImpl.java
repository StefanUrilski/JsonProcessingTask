package util;

import java.io.IOException;

import static common.Constants.INVALID_READ_WRITE_FILE_NAME_MESSAGE;

public class FileWriterImpl implements FileWriter {

    @Override
    public void write(String fileName, String text) throws IOException {
        String filePath = String.format("%s\\src\\main\\resources\\%s", System.getProperty("user.dir"), fileName);


        java.io.FileWriter writer;
        try {
            writer = new java.io.FileWriter(filePath);
        } catch (IOException e) {
            throw new IOException(INVALID_READ_WRITE_FILE_NAME_MESSAGE);
        }

        String[] data = text.split(System.lineSeparator());

        for (String dataLine : data) {
            writer.write(dataLine);
        }

        writer.close();
    }
}
