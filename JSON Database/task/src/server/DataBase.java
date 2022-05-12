package server;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


public abstract class DataBase {
    // I took an implementation of database from github
    // but I know how it works well
    private final static File file;
    static JsonObject data;
    JsonElement key;
    Response response = new Response();
    static final String NO_SUCH_KEY = "No such key";
    static {
        file = new File("src/server/data/db.json");
        try {
            String content = new String(Files.readAllBytes(Path.of(file.getPath())));
            data = new Gson().fromJson(content, JsonObject.class);
        } catch (IOException fnf) {
            fnf.printStackTrace();
        }
    }

    static void update() {
        try (FileWriter fileWriter = new FileWriter(file)) {
            if (data != null) {
                fileWriter.write(new Gson().toJson(data));
            }
        } catch (IOException fnf) {
            fnf.printStackTrace();
        }
    }

    public abstract String work();

    void checkDb() {
        if (data == null) {
            data = new JsonObject();
        }
    }
}
