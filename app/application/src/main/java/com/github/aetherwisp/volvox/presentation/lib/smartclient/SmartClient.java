package com.github.aetherwisp.volvox.presentation.lib.smartclient;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.github.aetherwisp.volvox.domain.optional.Suppliers;
import com.github.aetherwisp.volvox.presentation.PresentationConfiguration;

@Component("smartclient")
public class SmartClient {
    //======================================================================
    // Enums
    public static enum Skin {
        BLACK_OPS("BlackOps"),

        CASCADE("Cascade"),

        CUPERTIN("Cupertino"),

        ENTERPRISE("Enterprise"),

        ENTERPRISE_BLUE("EnterpriseBlue"),

        FLEET("fleet"),

        GRAPHITE("Graphite"),

        MOBILE("Mobile"),

        OBSIDIAN("Obsidian"),

        SILVER_WAVE("SilverWave"),

        SIMPLICITY("Simplicity"),

        SMART_CLIENT("SmartClient"),

        STANDARD("standard"),

        STRATUS("Stratus"),

        TAHOE("Tahoe"),

        TOOL_SKIN("ToolSkin"),

        TOOL_SKIN_NATIVE("ToolSkinNative"),

        TREE_FROG("TreeFrog"),

        TWILIGHT("Twilight");

        //======================================================================
        // Fields
        public static final String PARAMETER_NAME = "_skin";

        private static final Map<String, Skin> CACHE = Arrays.asList(Skin.values())
            .stream()
            .collect(Collectors.toMap(Skin::getLabel, v -> v, (v1, v2) -> v1));

        private final String label;

        //======================================================================
        // Constructors
        private Skin(final String _label) {
            this.label = _label;
        }

        //======================================================================
        // Methods
        public String getLabel() {
            return this.label;
        }

        public static Skin ofLabel(String _label) {
            final String label = Objects.requireNonNull(_label);
            return Optional.ofNullable(CACHE.get(label))
                .orElseThrow(Suppliers.unknownValue(label));
        }
    }

    //======================================================================
    // Constants
    public static final String PREFIX = PresentationConfiguration.PREFIX + ".lib.smartclient";

    //======================================================================
    // Fields
    private final String version;

    //======================================================================
    // Constructors
    @Autowired
    public SmartClient(@Value("${" + SmartClient.PREFIX + ".version}") final String _version) {
        this.version = Objects.requireNonNull(_version);
    }

    //======================================================================
    // Getters
    public String getVersion() {
        return this.version;
    }
}
