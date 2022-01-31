package com.replaymod.render;

import org.json.*;
import com.replaymod.render.PositionalInfo;

import java.util.UUID;

public class PlayerPositionalInfo extends PositionalInfo {
    /**
     * Object representing spatial information about a player object
     */

    private UUID uuid;

    public PlayerPositionalInfo(float posX, float posY, float posZ, float rotX, float rotY, float rotZ, UUID uuid) {
        super(posX, posY, posZ, rotX, rotY, rotZ);
        this.uuid = uuid;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject currentJson = super.getJsonObject();
        currentJson.put("uuid", uuid.toString());

        return currentJson;
    }
}
