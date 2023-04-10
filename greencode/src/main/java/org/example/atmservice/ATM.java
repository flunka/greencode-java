package org.example.atmservice;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import java.util.Objects;

@CompiledJson
class ATM {
    @JsonAttribute(name = "region")
    private final Integer region;
    @JsonAttribute(name = "atmId")
    private final Integer atmId;

    public ATM(Integer region, Integer atmId) {
        this.region = region;
        this.atmId = atmId;
    }

    public Integer getRegion() {
        return region;
    }

    public Integer getAtmId() {
        return atmId;
    }

    @Override
    public String toString() {
        return "ATM{region='" + region + '\'' + ", atmId=" + atmId + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ATM that = (ATM) o;
        return region.equals(that.region) && atmId.equals(that.atmId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(region, atmId);
    }
}
