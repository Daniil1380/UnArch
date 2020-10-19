package com.daniil1380.UnArch;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnArchiverZIP implements UnArchiver {
    private final String input;
    private final String output;

    public UnArchiverZIP(Archives.Archive archive) {
        this.output = "/" + archive.getDst() + "/";
        input = archive.getSrc();
    }

    public String getOutput() {
        return output;
    }

    public String getInputStream() {
        return input;
    }

    @Override
    public void unArchive() {
        try ( ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(input))){
            ZipEntry entry;
            String name;
            checkRepo(output);
            while ((entry = zipInputStream.getNextEntry()) != null) {
                name = entry.getName();
                if (entry.isDirectory()) {
                    checkRepo(output + name);
                } else {
                    try (FileOutputStream file =
                                new FileOutputStream(System.getProperty("user.home") + output + name)) {
                        int fileCode;
                        do {
                            fileCode = zipInputStream.read();
                            file.write(fileCode);
                        } while (fileCode != -1);
                        file.flush();
                        zipInputStream.closeEntry();
                    }
                }
            }
            System.out.println("Разархивирование файла в папку " + output + " завершено");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkRepo(String path) throws IOException {
        if (!Files.exists(Paths.get(System.getProperty("user.home") + path))) {
            Files.createDirectory(Paths.get(System.getProperty("user.home") + path));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnArchiverZIP obj = (UnArchiverZIP) o;
        return input.equals(obj.input) &&
                output.equals(obj.output);
    }

    @Override
    public int hashCode() {
        return input.hashCode() + output.hashCode();
    }
}
