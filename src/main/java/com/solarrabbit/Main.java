package com.solarrabbit;

import com.solarrabbit.file.Storage;

import org.json.simple.JSONObject;

/**
 * Driver class.
 */
public class Main {
    /**
     * The output file name.
     */
    private static final String output = "output.json";

    /**
     * Driver method.
     *
     * @param args input file name.
     */
    public static void main(String[] args) {
        Storage storage = new Storage(args[0], output);
        JSONObject input = storage.load();
        App app = App.of(input);
        long result = (long) app.run();
        storage.write(result);
    }
}
