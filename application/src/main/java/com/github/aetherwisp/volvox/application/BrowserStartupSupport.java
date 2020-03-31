package com.github.aetherwisp.volvox.application;

import static java.lang.invoke.MethodHandles.*;
import static org.apache.logging.log4j.LogManager.*;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class BrowserStartupSupport implements ApplicationListener<WebServerInitializedEvent> {
    //======================================================================
    // Fields
    private static final Logger LOG = getLogger(lookup().lookupClass());

    //======================================================================
    // Methods
    @Override
    public void onApplicationEvent(WebServerInitializedEvent _event) {
        //======================================================================
        // ポート番号を取得
        final Integer port = Integer.valueOf(_event.getWebServer()
                .getPort());

        //======================================================================
        // コンテキストパスを取得
        final String contextPath = _event.getApplicationContext()
                .getBean(Environment.class)
                .getProperty("server.servlet.context-path");

        //======================================================================
        // ブラウザを自動起動
        try {
            final Desktop desktop = Desktop.getDesktop();
            desktop.browse(URI.create(String.format("http://localhost:%d/%s", port, contextPath)));
        } catch (IOException e) {
            // ブラウザの自動起動に失敗した場合はユーザに手動でブラウザを起動してもらう
            LOG.warn(e.getMessage());
        }
    }
}
