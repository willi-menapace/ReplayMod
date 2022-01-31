package com.replaymod.render;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class AllTimestepInfo implements JsonSerializable {
    /**
     * Object representing spatial information about an object
     */

    private List<JsonSerializable> allObjects = new ArrayList<>();

    public AllTimestepInfo() {
    }

    public AllTimestepInfo(Collection<? extends JsonSerializable> allObjects) {
        this.allObjects.addAll(allObjects);
    }

    public void add(JsonSerializable object) {allObjects.add(object);
    }

    @Override
    public JSONObject getJsonObject() {

        JSONArray jsonArray = new JSONArray();
        for(JsonSerializable currentSerializableObject : allObjects) {
            JSONObject currentJsonObject = currentSerializableObject.getJsonObject();
            jsonArray.put(currentJsonObject);
        }

        JSONObject mainObj = new JSONObject();
        mainObj.put("timesteps", jsonArray);

        return mainObj;
    }
}
