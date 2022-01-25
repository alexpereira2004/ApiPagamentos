package br.com.lunacom.tools.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class MapperConfig {

    @Bean
    public ObjectMapper objectMapper(){
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();

        Converter<String, LocalDateTime> toLocalDateTime = new AbstractConverter<String, LocalDateTime>() {
            @Override
            protected LocalDateTime convert(String source) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm:ss");
                LocalDateTime localDate = LocalDateTime.parse(source, format);
                return localDate;
            }
        };
        modelMapper.addConverter(toLocalDateTime);

        Converter<LocalDateTime, String> toString = new AbstractConverter<LocalDateTime, String>() {
            @Override
            protected String convert(LocalDateTime source) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm:ss");
                String stringDateTime = source.format(format);
                return stringDateTime;
            }
        };
        modelMapper.addConverter(toLocalDateTime);
        modelMapper.addConverter(toString);
        return modelMapper;
    }

}
