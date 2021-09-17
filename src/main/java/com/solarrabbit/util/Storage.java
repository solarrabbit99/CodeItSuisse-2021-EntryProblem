package com.solarrabbit.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Storage {
    private static FileWriter fileWriter;

    private final String inputFilePath;

    private final String outputFilePath;

    public Storage(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    public JSONObject load() {
        JSONParser jsonParser = new JSONParser();
        JSONObject parsedInput = null;
        try (FileReader reader = new FileReader(inputFilePath)) {
            parsedInput = (JSONObject) jsonParser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return parsedInput;
    }

    public void write(long result) {
        HashMap<String, Long> jsonMap = new HashMap<>();
        jsonMap.put("value", result);
        JSONObject value = new JSONObject(jsonMap);
        String valueAsJsonString = value.toJSONString();
        try {
            fileWriter = new FileWriter(outputFilePath);
            fileWriter.write(valueAsJsonString);
            System.out.println(valueAsJsonString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
