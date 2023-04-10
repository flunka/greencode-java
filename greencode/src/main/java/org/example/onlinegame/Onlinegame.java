package org.example.onlinegame;

import java.io.IOException;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Iterator;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.JsonWriter;

import io.activej.http.AsyncServlet;
import io.activej.bytebuf.ByteBuf;
import io.activej.http.HttpResponse;
import io.activej.http.HttpRequest;

import org.example.onlinegame.Players;
import org.example.onlinegame.Clan;

public class Onlinegame {
    public static AsyncServlet execute(DslJson<?> dslJson) {
        return request -> request.loadBody()
                .map($ -> {
                    ByteBuf body = request.getBody();
                    try {
                        byte[] bodyBytes = body.getArray();
                        Players players = dslJson.deserialize(Players.class, bodyBytes,
                                bodyBytes.length);
                        ArrayList<Clan> clans = players.getClans();
                        Collections.sort(clans, new SortbyGameRules());
                        ArrayList<ArrayList<Clan>> order = Onlinegame.getGroupOrder(clans, players.getGroupCount());
                        JsonWriter writer = dslJson.newWriter();
                        try {
                            dslJson.serialize(writer, order);
                        } catch (IOException e) {
                            return HttpResponse.ofCode(400).withHtml("Invalid payload");
                        }
                        return HttpResponse.ok200().withJson(writer.toString());
                    } catch (IOException e) {
                        return HttpResponse.ofCode(400).withHtml("Invalid payload");
                    }
                });
    }

    static ArrayList<ArrayList<Clan>> getGroupOrder(ArrayList<Clan> clans, int groupCount) {
        ArrayList<ArrayList<Clan>> order = new ArrayList<ArrayList<Clan>>();

        while (!clans.isEmpty()) {
            Iterator<Clan> it = clans.iterator();
            ArrayList<Clan> groups = new ArrayList<Clan>();
            int groupSize = 0;
            while (it.hasNext()) {
                Clan clan = it.next();
                if (clan.getNumberOfPlayers() + groupSize > groupCount) {
                    continue;
                }
                groups.add(clan);
                groupSize += clan.getNumberOfPlayers();
                it.remove();
                if (groupSize < groupCount) {
                    break;
                }
            }
            order.add(groups);
        }
        return order;
    }

}

class SortbyGameRules implements Comparator<Clan> {

    @Override
    public int compare(Clan a, Clan b) {
        if (!a.getPoints().equals(b.getPoints())) {
            return b.getPoints() - a.getPoints();
        }
        return a.getNumberOfPlayers() - b.getNumberOfPlayers();
    }
}