package com.yvolabs.csv_to_json;

import com.yvolabs.csv_to_json.interfaces.FileReadable;
import com.yvolabs.csv_to_json.interfaces.MapDataToObject;
import com.yvolabs.csv_to_json.models.Vacancy;
import com.yvolabs.csv_to_json.services.CsvFileReader;
import com.yvolabs.csv_to_json.services.MapDataToVacancy;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class Main {

    public static void main(String[] args)  {
       try{
           FileReadable<String> fileReader = new CsvFileReader("/input.csv");
           Stream<String> data = fileReader.read();

           MapDataToObject<Vacancy> mapper = new MapDataToVacancy(data, ",");
           List<Vacancy> vacancies = mapper.map();

       }catch (Exception e){
           System.out.println(e.getMessage());
           e.printStackTrace();
       }
    }

}
