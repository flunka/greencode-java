package org.example;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.JsonWriter;
import io.activej.http.AsyncServlet;
import io.activej.bytebuf.ByteBuf;
import io.activej.http.HttpResponse;
import io.activej.http.HttpRequest;
import io.activej.common.collection.Either;
import io.activej.http.decoder.DecodeErrors;
import io.activej.http.decoder.Decoder;

import org.example.Task;
import org.example.ATM;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;

public class ATMService {

        public static AsyncServlet execute(DslJson<?> dslJson) {
                return request -> request.loadBody()
                                .map($ -> {
                                        ByteBuf body = request.getBody();
                                        try {
                                                byte[] bodyBytes = body.getArray();
                                                Task[] tasks = dslJson.deserialize(Task[].class, bodyBytes,
                                                                bodyBytes.length);
                                                Arrays.sort(tasks, new SortbyRules());
                                                ArrayList<ATM> atms = ATMService.getATMs(tasks);
                                                JsonWriter writer = dslJson.newWriter();
                                                try {
                                                        dslJson.serialize(writer, atms);
                                                } catch (IOException e) {
                                                        return HttpResponse.ofCode(400).withHtml(e.toString());
                                                }
                                                return HttpResponse.ok200().withJson(writer.toString());
                                        } catch (IOException e) {
                                                return HttpResponse.ofCode(400).withHtml(e.toString());
                                        }
                                });
        }

        private static ArrayList<ATM> getATMs(Task[] tasks) {
                HashSet<ATM> seen = new HashSet<ATM>();
                ArrayList<ATM> atms = new ArrayList<ATM>();
                for (Task task : tasks) {
                        ATM atm = new ATM(task.getRegion(), task.getAtmId());
                        if (seen.contains(atm)) {
                                continue;
                        }
                        atms.add(atm);
                        seen.add(atm);
                }
                return atms;
        }
}

class SortbyRules implements Comparator<Task> {
        private static final Map<String, Integer> taskPriorities;
        static {
                Map<String, Integer> aMap = new HashMap<String, Integer>();
                aMap.put("FAILURE_RESTART", 0);
                aMap.put("PRIORITY", 1);
                aMap.put("SIGNAL_LOW", 2);
                aMap.put("STANDARD", 3);
                taskPriorities = Collections.unmodifiableMap(aMap);
        }

        public int compare(Task a, Task b) {
                if (a.getRegion() != b.getRegion()) {
                        return a.getRegion() - b.getRegion();
                }
                return SortbyRules.taskPriorities.get(a.getRequestType())
                                - SortbyRules.taskPriorities.get(b.getRequestType());
        }
}