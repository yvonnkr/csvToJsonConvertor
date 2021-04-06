package com.yvolabs.csv_to_json.services;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.yvolabs.csv_to_json.models.Vacancy;
import com.yvolabs.csv_to_json.utils.CustomCharacterEscapes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code VacancyToJsonConvertor} converts {@code Vacancy} to json.
 * Extends {@code ObjectToJsonConvertor<>}
 * <p>
 * @author Yvonne Nkrunzanzi
 * @see ObjectToJsonConvertor
 * @since 1.0
 */
public class VacancyToJsonConvertor extends ObjectToJsonConvertor<Vacancy> {
    private final List<Vacancy> vacancies;

    public VacancyToJsonConvertor(List<Vacancy> vacancies) {
        super(vacancies);
        this.vacancies = vacancies;
    }

    /**
     * {@code serialize()}
     * Main method to run this convertor
     * <p></p>
     * {@code consoleWrite()} Writes to the console
     * {@code jsonFileWrite()} Writes to a json file
     */
    @Override
    public void serialize() {
        List<Vacancy> vacancyList = getVacancies();
        ObjectMapper mapper = getObjectMapper();
        consoleWrite(vacancyList, mapper);
        // jsonFileWrite(vacancyList,mapper);

    }

    /** Object mapper
     * @return {@code ObjectMapper}
     */
    private ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        CustomCharacterEscapes customCharacterEscapes = new CustomCharacterEscapes();
        mapper.getFactory().setCharacterEscapes(customCharacterEscapes);

        return mapper;
    }

    /** Writes to the console file - File located in resources folder
     * @param vacancyList list of vacancies
     * @param mapper Object mapper
     */
    private void consoleWrite(List<Vacancy> vacancyList, ObjectMapper mapper) {
        PropertyFilter customFilter = getCustomFilter();

        vacancyList.forEach(vacancy -> {
            String jsonString;
            try {
                FilterProvider filters = new SimpleFilterProvider().addFilter("customFilter", customFilter);
                mapper.setFilterProvider(filters);

                jsonString = mapper.writer(filters).writeValueAsString(vacancy);
                System.out.println(jsonString);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }


    /** Writes to a json file - File located in resources folder
     * @param vacancyList list of vacancies
     * @param mapper Object mapper
     */
    private void jsonFileWrite(List<Vacancy> vacancyList, ObjectMapper mapper) {
        String filePathWrite = "./src/main/resources/vacancies.json"; // hardcoded
        PropertyFilter customFilter = getCustomFilter();

        try {
            FilterProvider filters = new SimpleFilterProvider().addFilter("customFilter", customFilter);
            mapper.setFilterProvider(filters);

            mapper.writer(filters).writeValue(new File(filePathWrite), vacancyList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Custom {@code SimpleBeanPropertyFilter}
     * @return {@code PropertyFilter}
     */
    private PropertyFilter getCustomFilter() {
        PropertyFilter customFilter = new SimpleBeanPropertyFilter() {
            @Override
            public void serializeAsField
                    (Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {

                if (include(writer)) {
                    if (!writer.getName().equals("description")) {
                        writer.serializeAsField(pojo, jgen, provider);
                        return;
                    }
                    Vacancy vacancy = (Vacancy) pojo;
                    boolean toSerializeOrNot = !vacancy.getDescription().contains(",");
                    if (toSerializeOrNot) {
                        writer.serializeAsField(vacancy, jgen, provider);
                    }else {
                        jgen.writeRaw(",\n  \"description\"" + " : " + vacancy.getDescription());
                    }
                }  else if (!jgen.canOmitFields()) {

                }
            }

            @Override
            protected boolean include(BeanPropertyWriter writer) {
                return true;
            }
            @Override
            protected boolean include(PropertyWriter writer) {
                return true;
            }
        };
        return customFilter;
    }

    /** Removes null vacancies
     * @return {@code List<Vacancy>}
     */
    private List<Vacancy> getVacancies() {
        List<Vacancy> vacancyList = new ArrayList<>();

        for (Vacancy vacancy : this.vacancies) {
            if (vacancy != null) {
                vacancyList.add(vacancy);
            }
        }
        return vacancyList;
    }
}
