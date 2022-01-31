package com.replaymod.render;

import org.json.JSONObject;

public class CurrentTimestepInfo implements JsonSerializable {
    /**
     * Object representing the information for the current timestep including camera and entities
     */

    int frame;
    PositionalInfo cameraInfo;
    MultiobjectPositionalInfo entityInfo;

    public CurrentTimestepInfo(int frame, PositionalInfo cameraInfo, MultiobjectPositionalInfo entityInfo) {
        this.frame = frame;
        this.cameraInfo = cameraInfo;
        this.entityInfo = entityInfo;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject currentJson = new JSONObject();
        currentJson.put("camera", cameraInfo.getJsonObject());
        currentJson.put("entities", entityInfo.getJsonObject());
        currentJson.put("frame", frame);

        return currentJson;
    }
}
