package com.solarrabbit;

import com.solarrabbit.file.Storage;

import org.json.simple.JSONObject;

public class Main {
    private static final String output = "output.json";

    public static void main(String[] args) {
        Storage storage = new Storage(args[0], output);
        JSONObject input = storage.load();
        App app = App.of(input);
        long result = (long) app.run();
        storage.write(result);
    }
}
