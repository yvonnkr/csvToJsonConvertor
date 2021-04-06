package com.yvolabs.csv_to_json;

import com.yvolabs.csv_to_json.interfaces.FileReadable;
import com.yvolabs.csv_to_json.interfaces.MapDataToObject;
import com.yvolabs.csv_to_json.interfaces.ObjectToJson;
import com.yvolabs.csv_to_json.models.Vacancy;
import com.yvolabs.csv_to_json.services.CsvFileReader;
import com.yvolabs.csv_to_json.services.MapDataToVacancy;
import com.yvolabs.csv_to_json.services.VacancyToJsonConvertor;
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

           ObjectToJson convertor = new VacancyToJsonConvertor(vacancies);
           convertor.serialize();

       }catch (Exception e){
           System.out.println(e.getMessage());
           e.printStackTrace();
       }
    }

}
