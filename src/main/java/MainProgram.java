import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            JsonFIle jsonFIle = new JsonFIle(scanner.nextLine());
            String str = jsonFIle.json;
            ObjectMapper mapper = new ObjectMapper();
            Archives userFromJSON = mapper.readValue(str, Archives.class);
            for (Archive archive : userFromJSON.archives) {
                Thread threadForUnZip = new Thread(() -> {
                    try {
                        UnArchiver unArchiver = new UnArchiver(archive);
                        System.out.println("Файл найден " + archive.src);
                        unArchiver.unArchive();
                    } catch (FileNotFoundException e) {
                        System.out.println("Файл не найден " + archive.src);
                    }
                });
                threadForUnZip.start();
            }
        }
    }
}