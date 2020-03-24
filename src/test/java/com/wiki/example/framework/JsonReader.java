package com.wiki.example.framework;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonReader {

    public static Object readJson(String path, Class clazz) {
        Object object = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            object = mapper.readValue(new File(path), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }
}
