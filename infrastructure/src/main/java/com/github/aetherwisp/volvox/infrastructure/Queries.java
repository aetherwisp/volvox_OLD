package com.github.aetherwisp.volvox.infrastructure;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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
            return Optional.ofNullable(this.builder.toString())
                    .orElse("")
                    .replaceAll(" +", " ");
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
         * @param _sqlType the SQL type of the parameter
         * @return a reference to this parameter source, so it's possible to chain several calls together
         * @see {@link java.sql.Types}
         */
        public QueryParameters addValue(String _paramName, @Nullable Object _value, int _sqlType) {
            return this.addValue(_paramName, _value, _sqlType, true);
        }

        /**
         * Add a parameter to this parameter source if {@code _use} is true.
         * 
         * @param _paramName the name of the parameter
         * @param _value the value of the parameter
         * @param _sqlType the SQL type of the parameter
         * @param _use {@code true} if the parameter is added, {@code false} otherwise
         * @return a reference to this parameter source, so it's possible to chain several calls together
         * @see {@link java.sql.Types}
         */
        public QueryParameters addValue(String _paramName, @Nullable Object _value, int _sqlType, boolean _use) {
            if (_use) {
                this.values.put(Objects.requireNonNull(_paramName, "Parameter name must not be null"), _value);
                if (_value instanceof SqlParameterValue) {
                    this.registerSqlType(_paramName, ((SqlParameterValue) _value).getSqlType());
                } else {
                    this.registerSqlType(_paramName, _sqlType);
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
