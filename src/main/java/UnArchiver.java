import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnArchiver {
    private final ZipInputStream zipInputStream;
    private final String output;
    private static final String c = "C:\\"; // дикий костыль

    public UnArchiver(Archive archive) throws FileNotFoundException {
        this.output = archive.dst;
        zipInputStream = new ZipInputStream(new FileInputStream(c + archive.src));
    }

    public void unArchive(){
        try {
            ZipEntry entry;
            String name;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream file = new FileOutputStream(c + output + name);
                int fileCode;
                do {
                    fileCode = zipInputStream.read();
                    file.write(fileCode);
                } while (fileCode != -1);
                file.flush();
                zipInputStream.closeEntry();
                file.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnArchiver obj = (UnArchiver) o;
        return zipInputStream.equals(obj.zipInputStream) &&
                output.equals(obj.output);
    }

    @Override
    public int hashCode() {
        return zipInputStream.hashCode()+output.hashCode();
    }
}
