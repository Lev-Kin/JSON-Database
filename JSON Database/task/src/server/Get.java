package server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Get extends DataBase {

    public Get(JsonElement key) {
        super.key = key;
    }

    @Override
    public String work() {
        checkDb();
        if (key.isJsonArray()) {
            JsonArray path = key.getAsJsonArray();
            JsonObject tempDb = new Gson().fromJson(data, JsonObject.class);
            for (int i = 0; i < path.size(); i++) {
                if (tempDb.has(path.get(i).getAsString())) {
                    if (i == path.size() - 1) {
                        if (tempDb.get(path.get(i).getAsString()).isJsonPrimitive()) {
                            return response.getValue(tempDb.get(path.get(i).getAsString()).getAsString());
                        }
                        return response.getValue(tempDb.get(path.get(i).getAsString()).getAsJsonObject());
                    } else {
                        tempDb = tempDb.get(path.get(i).getAsString()).getAsJsonObject();
                    }
                } else {
                    return response.getError(NO_SUCH_KEY);
                }
            }
        } else {
            if (data.has(key.getAsString())) {
                if (data.get(key.getAsString()).isJsonPrimitive()) {
                    return response.getValue(data.get(key.getAsString()).getAsString());
                }
                return response.getValue(data.get(key.getAsString()).getAsJsonObject());
            }
        }
        return response.getError(NO_SUCH_KEY);
    }
}
