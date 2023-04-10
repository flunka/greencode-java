package org.example.onlinegame;

import java.util.ArrayList;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

import org.example.onlinegame.Clan;

@CompiledJson
class Players {
    @JsonAttribute(name = "groupCount", mandatory = true)
    private final Integer groupCount;
    @JsonAttribute(name = "clans", mandatory = true)
    private ArrayList<Clan> clans;

    public Players(Integer groupCount, ArrayList<Clan> clans) {
        this.groupCount = groupCount;
        this.clans = clans;
    }

    public Integer getGroupCount() {
        return groupCount;
    }

    public ArrayList<Clan> getClans() {
        return clans;
    }

    @Override
    public String toString() {
        return "Clan{groupCount='" + groupCount + '\'' + ", clans=" + clans.size() + '}';
    }
}
