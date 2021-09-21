package com.solarrabbit;

import com.solarrabbit.file.Storage;
import org.json.simple.JSONObject;

/**
 * Driver class.
 */
public class Main {
    private static final String OUTPUT_FILE_NAME = "output.json";

    /**
     * Driver method taking in a json file as an input.
     *
     * @param args input file name.
     * @throws ArrayIndexOutOfBoundsException if input file is not specified
     */
    public static void main(String[] args) {
        Storage storage = new Storage(args[0], OUTPUT_FILE_NAME);
        JSONObject input = storage.load();
        App app = App.of(input);
        long result = (long) app.run();
        storage.write(result);
    }
}
