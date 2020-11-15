package com.daniil1380.UnArch.services;

import com.daniil1380.UnArch.services.interfaces.UnArchiver;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnArchiverZIP implements UnArchiver {

    @Override
    public void unArchive(String input, String output) {
        output = "/" + output + "/";
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

}
