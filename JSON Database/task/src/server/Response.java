package server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class Response {
    private static final String OK = "OK";
    private static final String RESPONSE = "response";
    private static final String VALUE = "value";
    private static final String ERROR = "ERROR";
    private static final String REASON = "reason";
    private static final Gson gson = new Gson();

    String getSuccess() {
        return "";
    }

    String getError(String reason) {
        Map<String, String> map = new LinkedHashMap(Map.of(RESPONSE, ERROR));
        map.put(REASON, reason);
        return gson.toJson(map);
    }

    String getValue(String value) {
        Map<String, String> response = new LinkedHashMap<>();
        response.put(RESPONSE, OK);
        response.put(VALUE, value);
        return gson.toJson(response);
    }

    String getValue(JsonObject value) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put(RESPONSE, OK);
        response.put(VALUE, value);
        // System.out.println(gson.toJson(response));
        return gson.toJson(response);
    }
}
