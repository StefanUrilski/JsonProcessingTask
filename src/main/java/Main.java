import util.FileReader;
import util.FileReaderImpl;
import util.FileWriter;
import util.FileWriterImpl;

public class Main {

    public static void main(String[] args) {

        FileReader reader = new FileReaderImpl();

        FileWriter writer = new FileWriterImpl();

        System.out.println("It Works!");
    }
}
