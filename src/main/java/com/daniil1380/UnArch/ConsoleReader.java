package com.daniil1380.UnArch;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Scanner;

public class ConsoleReader {
    private final Scanner scanner;
    private final ObjectMapper objectMapper;

    public ConsoleReader(){
        scanner = new Scanner(System.in);
        objectMapper = new ObjectMapper();
    }

    public void start() throws IOException {
        while (true) {
            JsonFIle jsonFIle = new JsonFIle(scanner.nextLine());
            jsonFIle.loadJson();
            if (jsonFIle.getJson() != null) {
                Archives userFromJSON = objectMapper.readValue(jsonFIle.getJson(), Archives.class);
                for (Archives.Archive archive : userFromJSON.getArchives()) {
                    Thread threadForUnZip = new Thread(() -> {
                        UnArchiver unArchiver = new UnArchiver(archive);
                        if (unArchiver.getZipInputStream() != null) {
                            System.out.println("Файл найден " + archive.getSrc());
                            unArchiver.unArchive();
                        }
                    });
                    threadForUnZip.start();
                }
            }
        }
    }
}
