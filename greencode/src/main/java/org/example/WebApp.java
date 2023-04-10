package org.example;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.runtime.Settings;
import io.activej.http.AsyncServlet;
import io.activej.http.HttpResponse;
import io.activej.http.HttpRequest;
import io.activej.http.RoutingServlet;
import io.activej.inject.annotation.Provides;
import io.activej.launcher.Launcher;
import io.activej.launchers.http.HttpServerLauncher;
import io.activej.promise.Promise;
import org.example.ATMService;
import org.example.Onlinegame;

import org.example.Transactions;

import static io.activej.http.HttpMethod.GET;
import static io.activej.http.HttpMethod.POST;

public class WebApp extends HttpServerLauncher {

        public static final String ATM_ENDPOINT = "/atms/calculateOrder";
        public static final String ONLINEGAME_ENDPOINT = "/onlinegame/calculate";
        public static final String TRANSACTIONS_ENDPOINT = "/transactions/report";

        @Provides
        DslJson<?> dslJson() {
                return new DslJson<>(Settings.withRuntime().allowArrayFormat(true).includeServiceLoader());
        }

        @Provides
        AsyncServlet servlet(DslJson<?> dslJson) {
                return RoutingServlet.create()
                                .map(POST, ATM_ENDPOINT, ATMService.execute(dslJson))
                                .map(POST, ONLINEGAME_ENDPOINT, Onlinegame.execute(dslJson))
                                .map(POST, TRANSACTIONS_ENDPOINT, Transactions.execute(dslJson))

                                .map("/*", request -> HttpResponse.ofCode(404)
                                                .withHtml("<h1>404</h1><p>Path '"
                                                                + request.getRelativePath()
                                                                + "' not found</p>"));
        }

        public static void main(String[] args) throws Exception {
                Launcher launcher = new WebApp();
                launcher.launch(args);

        }

}
