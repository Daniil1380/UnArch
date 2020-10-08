package com.daniil1380.UnArch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonFIle {
    private String json;
    private final String fileName;

    public JsonFIle(String fileName) {
       this.fileName = fileName;
    }

    public void loadJson(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.lineSeparator();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(ls);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            json = stringBuilder.toString();
            reader.close();
        } catch (IOException e) {
            System.out.println("JSON-Файл не найден");
        }
    }

    public String getJson() {
        return json;
    }
}
