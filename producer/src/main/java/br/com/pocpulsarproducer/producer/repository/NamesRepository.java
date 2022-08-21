package br.com.pocpulsarproducer.producer.repository;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class NamesRepository {

    public List<String> getNames(int quantidade){

        List<String> records = new ArrayList<>();
        InputStream is = getClass().getResourceAsStream("/random_names.csv");
        Reader reader = new InputStreamReader(is);
        try (BufferedReader br = new BufferedReader(reader)) {
            String line;
            for(int i = 0; i <= quantidade && (line = br.readLine()) != null; i++) {
                records.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;

    }

}
