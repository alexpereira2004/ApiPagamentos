package br.com.lunacom.tools.converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface Converter<I, O> {

    O encode(I input);

    default List<O> encode(Collection<I> input) {
        return input.stream()
                .map(this::encode)
                .collect(Collectors.toList());
    }

}