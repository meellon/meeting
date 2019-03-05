package com.meellon.meetingroom.helper;

import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@Component
public class MapperHelper {
    public static <T> T convert(Object object, Class<T> clazz) {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper.map(object, clazz);
    }
}
