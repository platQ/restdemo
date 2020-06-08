package com.jplatkiw.RESTdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DriverController {

    @PostMapping(path = "/driver/create")
    public void create(@RequestBody Driver newDriver) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BufferedWriter writer = new BufferedWriter(new FileWriter("drivers.txt", true));

        try {
            String jsonDriver = mapper.writeValueAsString(newDriver);
            writer.write(jsonDriver);
            writer.newLine();
            writer.close();
            System.out.println(jsonDriver);

        } catch (JsonProcessingException jpe) {
            jpe.printStackTrace();
        }
    }

    @GetMapping(value = "/drivers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Driver> drivers() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("drivers.txt"));
        Gson gson = new GsonBuilder().create();
        JsonStreamParser parser = new JsonStreamParser(reader);
        List<Driver> driverList = new ArrayList<>();

        while(parser.hasNext()) {
            JsonElement element = parser.next();
            if (element.isJsonObject()) {
                Driver driver = gson.fromJson(element, Driver.class);
                driverList.add(driver);
            }
        }

        return driverList;
    }

    @GetMapping(value = "/drivers/byDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Driver> driversByDate(@RequestParam String date) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("drivers.txt"));
        Gson gson = new GsonBuilder().create();
        JsonStreamParser parser = new JsonStreamParser(reader);
        List<Driver> driverList = new ArrayList<>();

        while(parser.hasNext()) {
            JsonElement element = parser.next();
            if (element.isJsonObject()) {
                Driver driver = gson.fromJson(element, Driver.class);

                //compareTo returns a negative int if the second date is before the first
                if (0 > LocalDate.parse(date).compareTo(LocalDate.parse(driver.getCreationDate()))) {
                    driverList.add(driver);
                }
            }
        }

        return driverList;
    }
}
