package br.com.lunacom.tools.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonResourceObjectMapper<T> {
    private Class<T> model;

    public <T> List<T> loadTestJson(String fileName, Class<T> model) throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream inputStream= classLoader.getResourceAsStream(fileName);
        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, model);
        List<T> ts = mapper.readValue(inputStream, listType);
        return ts;
    }
}