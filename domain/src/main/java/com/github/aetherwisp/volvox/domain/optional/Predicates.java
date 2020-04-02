package com.github.aetherwisp.volvox.domain.optional;

import java.util.Collection;
import java.util.function.Predicate;

public final class Predicates {
    //======================================================================
    // Factories
    public static Predicate<String> notEmptyString() {
        return new NotEmptyString();
    }

    public static Predicate<Collection<?>> notEmptyCollection() {
        return new NotEmptyCollection();
    }

    //======================================================================
    // Classes
    private static class NotEmptyString implements Predicate<String> {
        @Override
        public boolean test(String _t) {
            return null != _t && !_t.isEmpty();
        }
    }

    private static class NotEmptyCollection implements Predicate<Collection<?>> {
        @Override
        public boolean test(Collection<?> _t) {
            return null != _t && !_t.isEmpty();
        }
    }
}
