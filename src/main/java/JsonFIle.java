import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JsonFIle {
    String json;

    public JsonFIle(String nextLine) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(nextLine));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        json = stringBuilder.toString();
    }
}
