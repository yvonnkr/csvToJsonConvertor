package com.yvolabs.csv_to_json.services;

import com.yvolabs.csv_to_json.models.Vacancy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

class MapDataToVacancyTest {

    @Test
    void map_doesNotThrow() {
        Stream<String> streamEmpty = Stream.empty();
        MapDataToVacancy mockedMapper = spy(new MapDataToVacancy(streamEmpty, ","));
        assertDoesNotThrow(mockedMapper::map);

    }

    @Test
    @DisplayName("map returns an array list")
    void map() {
        Stream<String> streamEmpty = Stream.empty();
        MapDataToVacancy mockedMapper = spy(new MapDataToVacancy(streamEmpty, ","));
        List<Vacancy> v = mockedMapper.map();
        assertEquals( ArrayList.class, v.getClass());

    }

}