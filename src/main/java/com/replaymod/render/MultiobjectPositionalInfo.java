package com.replaymod.render;

import com.google.gson.JsonObject;
import org.json.*;
import com.replaymod.render.JsonSerializable;

import java.util.ArrayList;
import java.util.List;


public class MultiobjectPositionalInfo implements JsonSerializable {
    /**
     * Object representing spatial information about an object
     */

    private List<JsonSerializable> allObjects = new ArrayList<>();

    public MultiobjectPositionalInfo() {
    }

    public void add(JsonSerializable object) {
        allObjects.add(object);
    }

    @Override
    public JSONObject getJsonObject() {

        JSONArray jsonArray = new JSONArray();
        for(JsonSerializable currentSerializableObject : allObjects) {
            JSONObject currentJsonObject = currentSerializableObject.getJsonObject();
            jsonArray.put(currentJsonObject);
        }

        JSONObject mainObj = new JSONObject();
        mainObj.put("objects", jsonArray);

        return mainObj;
    }
}
