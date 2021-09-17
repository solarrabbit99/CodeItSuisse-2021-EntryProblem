package com.solarrabbit;

import org.json.simple.JSONObject;

import com.solarrabbit.util.Storage;

public class Main {
    private static final String input = "./data/sampleInput4.json";
    private static final String output = "./data/sampleOutput4.json";

    public static void main(String[] args) {
        Storage storage = new Storage(input, output);
        JSONObject input = storage.load();
        App app = App.of(input);
        long result = (long) app.run();
        storage.write(result);
    }
}
