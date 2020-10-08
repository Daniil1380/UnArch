package com.daniil1380.UnArch;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnArchiver {
    private ZipInputStream zipInputStream;
    private String output;

    public UnArchiver(Archives.Archive archive) {
        try {
            this.output = archive.getDst();
            zipInputStream = new ZipInputStream(new FileInputStream(archive.getSrc()));
        } catch (FileNotFoundException e) {
            System.out.println("Файл  не найден " + archive.getSrc());
        }
    }

    public String getOutput() {
        return output;
    }

    public ZipInputStream getZipInputStream() {
        return zipInputStream;
    }

    public void unArchive(){
        try {
            ZipEntry entry;
            String name;
            while ((entry = zipInputStream.getNextEntry()) != null) {
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
                        fileCode = zipInputStream.read();
                        file.write(fileCode);
                    } while (fileCode != -1);
                    file.flush();
                    zipInputStream.closeEntry();
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
        UnArchiver obj = (UnArchiver) o;
        return zipInputStream.equals(obj.zipInputStream) &&
                output.equals(obj.output);
    }

    @Override
    public int hashCode() {
        return zipInputStream.hashCode()+output.hashCode();
    }
}
