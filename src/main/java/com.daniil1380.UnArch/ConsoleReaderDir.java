package main.java.com.daniil1380.UnArch;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Scanner;

public class ConsoleReaderDir implements ReaderDir{
    private final Scanner scanner;
    private final ObjectMapper objectMapper;

    public ConsoleReaderDir(){
        scanner = new Scanner(System.in);
        objectMapper = new ObjectMapper();
    }

    @Override
    public void start() {
        while (true) {
            JsonFIleLoader jsonFIle = new JsonFIleLoader(scanner.nextLine());
            jsonFIle.loadFile();
            if (jsonFIle.getFile() != null) {
                try {
                    Archives userFromJSON = objectMapper.readValue(jsonFIle.getFile(), Archives.class);
                    new JsonClassIterator().iterate(userFromJSON);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
