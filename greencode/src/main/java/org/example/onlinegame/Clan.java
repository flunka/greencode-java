package org.example.onlinegame;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

@CompiledJson
class Clan {
    @JsonAttribute(name = "numberOfPlayers", mandatory = true)
    private final Integer numberOfPlayers;
    @JsonAttribute(name = "points", mandatory = true)
    private final Integer points;

    public Clan(Integer numberOfPlayers, Integer points) {
        this.numberOfPlayers = numberOfPlayers;
        this.points = points;
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public Integer getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Clan{numberOfPlayers='" + numberOfPlayers + '\'' + ", points=" + points + '}';
    }
}
