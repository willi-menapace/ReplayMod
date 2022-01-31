package com.replaymod.render;

import org.json.*;

public interface JsonSerializable {
    /**
     * Any object that can be serialized to JSON
     */
    public JSONObject getJsonObject();
}
