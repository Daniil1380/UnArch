package com.daniil1380.UnArch;

import java.io.*;
import org.apache.commons.io.FileUtils;


public class JsonFIle {
    private String json;
    private final String fileName;

    public JsonFIle(String fileName) {
       this.fileName = fileName;
    }

    public void loadJson(){
        try {
            json = FileUtils.readFileToString(new File(fileName));
        } catch (IOException e) {
            System.out.println("JSON-Файл не найден");
        }
    }

    public String getJson() {
        return json;
    }
}
