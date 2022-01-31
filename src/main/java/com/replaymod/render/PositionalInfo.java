package com.replaymod.render;

import com.google.gson.JsonObject;
import org.json.*;
import com.replaymod.render.JsonSerializable;

public class PositionalInfo implements JsonSerializable {
    /**
     * Object representing spatial information about an object
     */

    private float posX;
    private float posY;
    private float posZ;
    private float rotX;
    private float rotY;
    private float rotZ;

    public PositionalInfo(float posX, float posY, float posZ, float rotX, float rotY, float rotZ) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;

        this.rotX = rotX % 360F;
        this.rotY = rotX % 360F;
        this.rotZ = rotX % 360F;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject currentJson = new JSONObject();
        currentJson.put("posX", posX);
        currentJson.put("posY", posY);
        currentJson.put("posZ", posZ);
        currentJson.put("rotX", rotX);
        currentJson.put("rotY", rotY);
        currentJson.put("rotZ", rotZ);

        return currentJson;
    }
}
