package org.example.atmservice;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

@CompiledJson
class Task {
    @JsonAttribute(name = "region", mandatory = true)
    private final Integer region;
    @JsonAttribute(name = "requestType", mandatory = true)
    private final String requestType;
    @JsonAttribute(name = "atmId", mandatory = true)
    private final Integer atmId;

    public Task(Integer region, String requestType, Integer atmId) {
        this.region = region;
        this.requestType = requestType;
        this.atmId = atmId;
    }

    public Integer getRegion() {
        return region;
    }

    public String getRequestType() {
        return requestType;
    }

    public Integer getAtmId() {
        return atmId;
    }

    @Override
    public String toString() {
        return "Task{region='" + region + '\'' + ", requestType=" + requestType + ", atmId=" + atmId + '}';
    }
}
