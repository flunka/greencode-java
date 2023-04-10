package org.example.onlinegame;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

import org.example.onlinegame.Clan;

@CompiledJson
class Players {
    @JsonAttribute(name = "groupCount")
    private final Integer groupCount;
    @JsonAttribute(name = "clans")
    private final Clan[] clans;

    public Players(Integer groupCount, Clan[] clans) {
        this.groupCount = groupCount;
        this.clans = clans;
    }

    public Integer getGroupCount() {
        return groupCount;
    }

    public Clan[] getClans() {
        return clans;
    }

    @Override
    public String toString() {
        return "Clan{groupCount='" + groupCount + '\'' + ", clans=" + clans.length + '}';
    }
}
