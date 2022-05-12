package server;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class Delete extends DataBase {
    public Delete(JsonElement key) {
        super.key = key;
    }

    @Override
    public String work() {
        checkDb();
        if (key.isJsonArray()) {
            JsonArray path = key.getAsJsonArray();
            JsonObject tempDb = data;
            for (int i = 0; i < path.size(); i++) {
                if (tempDb.has(path.get(i).getAsString())) {
                    if (i == path.size() - 1) {
                        tempDb.remove(path.get(i).getAsString());
                        return response.getSuccess();
                    } else {
                        tempDb = tempDb.get(path.get(i).getAsString()).getAsJsonObject();
                    }
                } else {
                    return response.getError(NO_SUCH_KEY);
                }
            }
        } else {
            if (data.has(key.getAsString())) {
                data.remove(key.getAsString());
                return response.getSuccess();
            }
        }
        return response.getError(NO_SUCH_KEY);
    }
}
