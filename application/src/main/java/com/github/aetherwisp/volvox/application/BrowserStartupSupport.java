package com.github.aetherwisp.volvox.application;

import static java.lang.invoke.MethodHandles.*;
import static org.apache.logging.log4j.LogManager.*;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import javafx.application.Application;
import javafx.stage.Stage;

// @Component
public class BrowserStartupSupport extends Application implements ApplicationListener<WebServerInitializedEvent> {
    //======================================================================
    // Fields
    private static final Logger LOG = getLogger(lookup().lookupClass());

    private Stage primaryStage;

    //======================================================================
    // Methods
    @Override
    public void onApplicationEvent(WebServerInitializedEvent _event) {
        LOG.info("ポート番号：{}", Integer.valueOf(_event.getWebServer()
                .getPort()));
    }

    @Override
    public void start(Stage _primaryStage) throws Exception {
        LOG.info("JavaFX start");
        this.primaryStage = _primaryStage;
    }
}
