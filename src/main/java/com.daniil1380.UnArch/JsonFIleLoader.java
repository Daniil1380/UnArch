package com.daniil1380.UnArch;

import java.io.*;
import org.apache.commons.io.FileUtils;


public class JsonFIleLoader implements FileLoader {
    private String file;
    private final String fileName;

    public JsonFIleLoader(String fileName) {
       this.fileName = fileName;
    }

    @Override
    public void loadFile(){
        try {
            file = FileUtils.readFileToString(new File(fileName));
        } catch (IOException e) {
            System.out.println("JSON-Файл не найден");
        }
    }

    public String getFile() {
        return file;
    }
}
