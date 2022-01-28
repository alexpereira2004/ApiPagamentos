package br.com.lunacom.tools.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class JsonLoader {

    public static  <T> List<T> getContentFromFile(String fileName, Class<T> model) {
        JsonResourceObjectMapper jsonResourceObjectMapper  = new JsonResourceObjectMapper();
        List<T> list = null;
        try {
            list = jsonResourceObjectMapper.loadTestJson(fileName, model);
        } catch (IOException e) {
            log.error("Error DataLoader, {}", e.getMessage());
        }
        return list;
    }

    public static String getContentFromFile(String fileName) throws IOException {
        JsonLoader jsonLoader = new JsonLoader();
        ClassLoader classLoader = jsonLoader.getClass().getClassLoader();
        InputStream inputStream= classLoader.getResourceAsStream(fileName);
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }
}