package com.joe.midpoint.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;

public class Update {
    public Update(Integer deviceId, HashMap<String, Object> payload) {
        this.payload = payload;
        this.deviceId = deviceId;
        this.creationDate = new Date();
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public HashMap<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(HashMap<String, Object> payload) {
        this.payload = payload;
    }

    public Date getCreationDate() { return creationDate;}

    @NotNull
    private Integer deviceId;
    @NotEmpty
    private HashMap<String, Object> payload;
    private Date creationDate;
}
