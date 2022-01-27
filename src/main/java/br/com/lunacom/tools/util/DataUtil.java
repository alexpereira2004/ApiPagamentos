package br.com.lunacom.tools.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataUtil {
    public static LocalDateTime stringParaLocalDateTime(String source) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm:ss");
        LocalDateTime localDate = LocalDateTime.parse(source, format);
        return localDate;
    }

    public static String localDateTimeParaString(LocalDateTime source) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm:ss");
        String stringDateTime = source.format(format);
        return stringDateTime;
    }
}
