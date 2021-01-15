package com.github.aetherwisp.volvox;

import java.net.URL;

public interface Menu {

    /** メニューグループ */
    String getGroup();

    /** メニュー名 */
    String getName();

    /** 遷移先 URL */
    URL getUrl();
}
