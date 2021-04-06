package com.yvolabs.csv_to_json.services;

import com.yvolabs.csv_to_json.models.Vacancy;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.spy;


class ObjectToJsonConvertorTest {

    @Test
    void serialize_doesNotThrow() {
        Stream<String> streamEmpty = Stream.empty();
        MapDataToVacancy mockedMapper = spy(new MapDataToVacancy(streamEmpty, ","));
        List<Vacancy> vacancies = mockedMapper.map();
        VacancyToJsonConvertor convertor = spy(new VacancyToJsonConvertor(vacancies));

        assertDoesNotThrow(convertor::serialize);
    }
}