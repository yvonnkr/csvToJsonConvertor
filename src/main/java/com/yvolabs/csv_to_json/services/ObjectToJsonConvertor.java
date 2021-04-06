package com.yvolabs.csv_to_json.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yvolabs.csv_to_json.interfaces.ObjectToJson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code ObjectToJsonConvertor} converts object to json.
 * Implements {@code ObjectToJson}
 * <p>
 * @author Yvonne Nkrunzanzi
 * @see ObjectToJson
 * @since 1.0
 */
public class ObjectToJsonConvertor<T> implements ObjectToJson {
    private final List<T> objects;

    public ObjectToJsonConvertor(List<T> objects) {
        this.objects = objects;
    }

    /**
     * Main method to run convertor
     */
    @Override
    public void serialize()  {
        List<T> objectList = getObjects();
        ObjectMapper mapper = getObjectMapper();
        consoleWrite(objectList, mapper);
    }


    /**
     * @return {@code ObjectMapper}
     */
    private ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }

    /** Writes to console
     * @param objectList List of objects
     * @param mapper The mapper to use
     */
    private void consoleWrite(List<T> objectList, ObjectMapper mapper) {
        objectList.forEach(object -> {
            String jsonString;
            try {
                jsonString = mapper.writeValueAsString(object);
                System.out.println(jsonString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /** Writes to file
     * @param objectList List of objects
     * @param mapper The mapper to use
     */
    private void jsonFileWrite(List<T> objectList, ObjectMapper mapper) {
        String filePathWrite = "./src/main/resources/vacancies.json"; // hardcoded
        try {
            mapper.writeValue(new File(filePathWrite), objectList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Removes nulls
     * @return {@code List}
     */
    private List<T> getObjects() {
        List<T> objects1 = new ArrayList<>();

        for (Object object : this.objects) {
            if (object != null) {
                objects1.add((T) object);
            }
        }
        return objects1;
    }

}
