package com.github.aetherwisp.volvox;

import static java.lang.invoke.MethodHandles.*;
import static org.apache.logging.log4j.LogManager.*;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

// @Component
public class JavaFxApplication extends Application implements ApplicationListener<WebServerInitializedEvent> {
    //======================================================================
    // Fields
    private static final Class<JavaFxApplication> CLASS = JavaFxApplication.class;
    private static final Logger LOG = getLogger(lookup().lookupClass());

    private static int port;

    private ConfigurableApplicationContext applicationContext;

    //======================================================================
    // Methods
    @Override
    public void onApplicationEvent(WebServerInitializedEvent _event) {
        port = _event.getWebServer()
                .getPort();
    }

    @Override
    public void init() throws Exception {
        LOG.debug("{}#init starting.", CLASS.getSimpleName());

        this.applicationContext = new SpringApplicationBuilder().sources(VolvoxApplication.class)
                .run(getParameters().getRaw()
                        .toArray(new String[0]));

        LOG.debug("{}#init finished.", CLASS.getSimpleName());
    }

    @Override
    public void start(Stage _primaryStage) throws Exception {
        LOG.debug("http://localhost:{}/volvox", Integer.valueOf(port));

        final WebView view = new WebView();
        view.setContextMenuEnabled(true);

        final WebEngine engine = view.getEngine();
        engine.setJavaScriptEnabled(true);
        engine.setOnAlert(event -> LOG.error(event));
        engine.setOnError(event -> LOG.error(event));
        engine.load("http://localhost:" + port + "/volvox");

        final Scene scene = new Scene(view, 1024, 768);
        scene.getStylesheets()
                .add("/volvox/adminlte/3.0.2/plugins/fontawesome-free/css/all.min.css");

        _primaryStage.setScene(scene);
        _primaryStage.setTitle("Volvox");
        _primaryStage.setMinWidth(1024d);
        _primaryStage.setMinHeight(768d);
        _primaryStage.show();
    }
}
