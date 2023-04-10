package org.example;

import com.dslplatform.json.DslJson;

import io.activej.http.AsyncServlet;
import io.activej.http.HttpResponse;
import io.activej.http.HttpRequest;

public class Onlinegame {
    public static AsyncServlet execute(DslJson<?> dslJson) {
        return request -> {
            String userId = request.getPathParameter("user_id");
            return HttpResponse.ok200()
                    .withJson("{}");
        };
    }
}
