package com.github.aetherwisp.volvox.infrastructure;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.namedparam.AbstractSqlParameterSource;
import org.springframework.lang.Nullable;

public final class Queries {
    private Queries() {
        throw new UnsupportedOperationException();
    }

    //======================================================================
    // Methods
    public static QueryBuilder query() {
        return new QueryBuilder();
    }

    public static QueryParameters parameters() {
        return new QueryParameters();
    }

    //======================================================================
    // Classes
    public static final class QueryBuilder {
        //======================================================================
        // Fields
        private final StringBuilder builder;

        //======================================================================
        // Constructors
        private QueryBuilder() {
            this.builder = new StringBuilder();
        }

        //======================================================================
        // Methods
        public QueryBuilder append(String _str) {
            this.builder.append(_str);
            return this;
        }

        public QueryBuilder append(String _str, boolean _append) {
            if (_append) {
                this.builder.append(_str);
            }
            return this;
        }

        @Override
        public String toString() {
            return this.builder.toString();
        }
    }

    public static final class QueryParameters extends AbstractSqlParameterSource {
        //======================================================================
        // Fields
        private final Map<String, Object> values = new LinkedHashMap<>();

        //======================================================================
        // Constructors
        private QueryParameters() {}

        //======================================================================
        // Methods
        /**
         * Add a parameter to this parameter source.
         * 
         * @param _paramName the name of the parameter
         * @param _value the value of the parameter
         * @return a reference to this parameter source, so it's possible to chain several calls together
         */
        public QueryParameters addValue(String _paramName, @Nullable Object _value) {
            return this.addValue(_paramName, _value, true);
        }

        /**
         * Add a parameter to this parameter source if {@code _use} is true.
         * 
         * @param _paramName the name of the parameter
         * @param _value the value of the parameter
         * @param _use {@code true} if the parameter is added, {@code false} otherwise
         * @return a reference to this parameter source, so it's possible to chain several calls together
         */
        public QueryParameters addValue(String _paramName, @Nullable Object _value, boolean _use) {
            if (_use) {
                this.values.put(Objects.requireNonNull(_paramName, "Parameter name must not be null"), _value);
                if (_value instanceof SqlParameterValue) {
                    this.registerSqlType(_paramName, ((SqlParameterValue) _value).getSqlType());
                }
            }
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasValue(String _paramName) {
            return this.values.containsKey(_paramName);
        }

        /**
         * {@inheritDot@rt}
         */
        @Override
        public Object getValue(String _paramName) throws IllegalArgumentException {
            return Collections.unmodifiableMap(this.values);
        }
    }
}
