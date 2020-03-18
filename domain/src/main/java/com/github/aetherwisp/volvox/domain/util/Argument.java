package com.github.aetherwisp.volvox.domain.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

public final class Argument<T> {
    //======================================================================
    // Constants
    private static final Argument<?> EMPTY = new Argument<>();
    private static final String MSG_GTE = "Value must be greater than or equal to '%s'.";
    private static final String MSG_LTE = "Value must be less than or equal to '%s'.";

    //======================================================================
    // Fields
    private final T value;

    //======================================================================
    // Constructors
    private Argument() {
        this.value = null;
    }

    private Argument(final T _value) {
        this.value = Objects.requireNonNull(_value, "Argument must not be null.");
    }

    //======================================================================
    // Factories
    public static <T> Argument<T> required(T _value) {
        return new Argument<T>(_value);
    }

    @SuppressWarnings("unchecked")
    public static <T> Argument<T> nullable(T _value) {
        return null == _value ? (Argument<T>) EMPTY : required(_value);
    }

    //======================================================================
    // Methods
    public final T get() {
        return this.value;
    }

    public final T get(T _defaultValue) {
        return null != this.value ? this.value : _defaultValue;
    }

    public final <R> R convert(Function<T, R> _converter) {
        return _converter.apply(this.value);
    }

    public final Argument<T> length(int _length) {
        if (null == this.value) {
            return this;
        }

        final Class<?> type = this.value.getClass();
        if (type.isArray()) {
            final Object[] array = (Object[]) this.value;
            if (_length == array.length) {
                return this;
            } else {
                throw illegal("Length must be " + _length + ".");
            }
        }

        if (Collection.class.isAssignableFrom(type)) {
            if (_length == ((Collection<?>) this.value).size()) {
                return this;
            } else {
                throw illegal("Must not contain null.");
            }
        }

        return this;
    }

    public final Argument<T> notContainsNull() {
        if (null == this.value) {
            return this;
        }

        final Class<?> type = this.value.getClass();
        if (type.isArray()) {
            if (Arrays.stream((Object[]) this.value)
                    .anyMatch(v -> null == v)) {
                throw illegal("Must not contain null.");
            }
        }

        if (Collection.class.isAssignableFrom(type)) {
            if (((Collection<?>) this.value).stream()
                    .anyMatch(v -> null == v)) {
                throw illegal("Must not contain null.");
            }
        }

        return this;
    }

    @SuppressWarnings("unchecked")
    public final Argument<T> anyMatch(T _firstValue, T... _remainingValues) {
        if (null == this.value) {
            if (null == _firstValue) {
                return this;
            } else {
                for (T _value : _remainingValues) {
                    if (null == _value) {
                        return this;
                    }
                }
            }
        } else {
            if (this.value.equals(_firstValue)) {
                return this;
            } else {
                for (T _value : _remainingValues) {
                    if (this.value.equals(_value)) {
                        return this;
                    }
                }
            }
        }

        throw illegal("No matched values.");
    }

    public final Argument<T> greaterThanEquals(int _spec) {
        if (null != this.value && this.value instanceof Number) {
            if (!(_spec <= ((Number) this.value).intValue())) {
                throw illegal(String.format(MSG_GTE, Integer.valueOf(_spec)));
            }
        }
        return this;
    }

    public final Argument<T> greaterThanEquals(float _spec) {
        if (null != this.value && this.value instanceof Number) {
            if (!(_spec <= ((Number) this.value).floatValue())) {
                throw illegal(String.format(MSG_GTE, Float.valueOf(_spec)));
            }
        }
        return this;
    }

    public final Argument<T> greaterThanEquals(double _spec) {
        if (null != this.value && this.value instanceof Number) {
            if (!(_spec <= ((Number) this.value).doubleValue())) {
                throw illegal(String.format(MSG_GTE, Double.valueOf(_spec)));
            }
        }
        return this;
    }

    public final Argument<T> lessThan(int _spec) {
        if (null != this.value && this.value instanceof Number) {
            if (!(_spec > ((Number) this.value).intValue())) {
                throw illegal(String.format(MSG_LTE, Integer.valueOf(_spec)));
            }
        }
        return this;
    }

    public final Argument<T> lessThanEquals(int _spec) {
        if (null != this.value && this.value instanceof Number) {
            if (!(_spec >= ((Number) this.value).intValue())) {
                throw illegal(String.format(MSG_LTE, Integer.valueOf(_spec)));
            }
        }
        return this;
    }

    public final Argument<T> lessThanEquals(float _spec) {
        if (null != this.value && this.value instanceof Number) {
            if (!(_spec >= ((Number) this.value).floatValue())) {
                throw illegal(String.format(MSG_LTE, Float.valueOf(_spec)));
            }
        }
        return this;
    }

    public static IllegalArgumentException illegal(String _message) {
        return new IllegalArgumentException(_message);
    }
}
