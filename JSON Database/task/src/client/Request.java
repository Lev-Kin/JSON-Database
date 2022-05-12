package client;

import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Request {
    @Parameter(names = "-t", description = "type of operation")
    private String type;
    @Parameter(names = "-k", description = "key")
    private String key;
    @Parameter(names = "-v", description = "a value")
    private String value;
    @Parameter(names = "-in", description = "name of a file")
    @Expose
    private String dataSource;

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    String readFile() {
        String message = null;
        try {
            Path filePath = Paths.get("src/client/data/" + dataSource);
            message = new String(Files.readAllBytes(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
}
