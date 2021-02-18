package com.github.aetherwisp.volvox.presentation.header;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Setting {
    //======================================================================
    // Fields
    private final String title;

    private List<Setting> children;

    //======================================================================
    // Constructors
    public Setting(final String _title) {
        this(_title, new ArrayList<>());
    }

    public Setting(final String _title, final List<Setting> _children) {
        this.title = Objects.requireNonNull(_title);
        this.children = Collections.unmodifiableList(new ArrayList<>(Optional.ofNullable(_children)
            .orElse(new ArrayList<>())));
    }

    public Setting(final String _title, final Setting... _children) {
        this(_title, Arrays.asList(_children));
    }

    private Setting(final List<Setting> _children) {
        this.title = null;
        this.children = Collections.unmodifiableList(new ArrayList<>(Optional.ofNullable(_children)
            .orElse(new ArrayList<>())));
    }

    //======================================================================
    // Methods
    public static Setting root(final Setting... _children) {
        return new Setting(Arrays.asList(_children));
    }

    public static Setting root(final List<Setting> _children) {
        return new Setting(_children);
    }

    //======================================================================
    // Getters
    public String getTitle() {
        return this.title;
    }

    public List<Setting> getChildren() {
        return this.children;
    }
}
