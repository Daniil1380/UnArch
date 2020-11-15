package com.daniil1380.UnArch.utils;

import com.daniil1380.UnArch.model.Archives;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ReaderJson {

    public static Archives readFile(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
            try {
                String json = FileUtils.readFileToString(new File(path));
                if (json != null) {
                    return objectMapper.readValue(json, Archives.class);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
    }
}
