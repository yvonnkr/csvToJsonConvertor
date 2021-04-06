package com.yvolabs.csv_to_json;

import com.yvolabs.csv_to_json.interfaces.FileReadable;
import com.yvolabs.csv_to_json.services.CsvFileReader;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Stream;

@SpringBootApplication
public class Main {

    public static void main(String[] args)  {
       try{
           FileReadable<String> fileReader = new CsvFileReader("/input.csv");
           Stream<String> data = fileReader.read();

       }catch (Exception e){
           System.out.println(e.getMessage());
           e.printStackTrace();
       }
    }

}
