package server;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class Set extends DataBase {

    private JsonElement value;

    public Set(JsonElement key, JsonElement value) {
        super.key = key;
        this.value = value;
    }

    @Override
    public String work() {
        checkDb();
        JsonObject tempDb = data;
        if (key.isJsonArray()) {
            JsonArray path = key.getAsJsonArray();
            for (int i = 0; i < path.size(); i++) {
                if (i == path.size() - 1) {
                    tempDb.add(path.get(i).getAsString(), value);
                    break;
                } else if (!tempDb.has(path.get(i).getAsString())) {
                    tempDb.add(path.get(i).getAsString(), new JsonObject());
                }
                tempDb = tempDb.getAsJsonObject(path.get(i).getAsString());
            }
        } else {
            data.add(key.getAsString(), value);
        }
        DataBase.update();
        return response.getSuccess();
    }
}
