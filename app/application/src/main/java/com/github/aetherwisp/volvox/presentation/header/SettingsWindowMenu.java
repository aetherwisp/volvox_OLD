package com.github.aetherwisp.volvox.presentation.header;

public class SettingsWindowMenu {
    //======================================================================
    // Fields
    /** インデント用の余白カラム */
    private final String spacer = "";

    /** メニューグループ名 */
    private final String group;

    /** メニュー名 */
    private final String name;

    /** 遷移先 URL */
    private final String url;

    //======================================================================
    // Constructors
    public SettingsWindowMenu(String _group, String _name, String _url) {
        this.group = _group;
        this.name = _name;
        this.url = _url;
    }

    //======================================================================
    // Getters
    public String getSpacer() {
        return this.spacer;
    }

    public String getGroup() {
        return this.group;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }
}
