package main.java.com.daniil1380.UnArch;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnArchiverZIP implements UnArchiver{
    private ZipInputStream inputStream;
    private String output;

    public UnArchiverZIP(Archives.Archive archive) {
        try {
            this.output = archive.getDst() + "/";
            inputStream = new ZipInputStream(new FileInputStream(archive.getSrc()));
        } catch (FileNotFoundException e) {
            System.out.println("Файл  не найден " + archive.getSrc());
        }
    }

    public String getOutput() {
        return output;
    }

    public ZipInputStream getInputStream() {
        return inputStream;
    }

    @Override
    public void unArchive(){
        try {
            ZipEntry entry;
            String name;
            while ((entry = inputStream.getNextEntry()) != null) {
                name = entry.getName();
                if (entry.isDirectory()){
                    if (!Files.exists(Paths.get(output + name))) {
                        Files.createDirectory(Paths.get(output + name));
                    }
                }
                else {
                    FileOutputStream file = new FileOutputStream(output + name);
                    int fileCode;
                    do {
                        fileCode = inputStream.read();
                        file.write(fileCode);
                    } while (fileCode != -1);
                    file.flush();
                    inputStream.closeEntry();
                    file.close();
                }
            }
            System.out.println("Разархивирование файла в папку " + output + " завершено");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnArchiverZIP obj = (UnArchiverZIP) o;
        return inputStream.equals(obj.inputStream) &&
                output.equals(obj.output);
    }

    @Override
    public int hashCode() {
        return inputStream.hashCode()+output.hashCode();
    }
}
