package com.example.products.common.util;

import org.slf4j.Logger;

public final class ExceptionUtils {

    private ExceptionUtils() {
        throw new UnsupportedOperationException("ERR_20200416155330069");
    }

    public static void notNull(final Object object, final String errorMessage, final Logger log) {
        if (object == null) {
            throwIllegalArgument(errorMessage, log);
        }
    }

    public static void throwIllegalArgument(final String errorMessage, final Logger log) {
        throwRuntime(new IllegalArgumentException(), errorMessage, log);
    }

    public static void throwIllegalState(String errorMessage, Logger log) {
        throwRuntime(new IllegalStateException(), errorMessage, log);
    }

    public static void throwUnsupportedOperation(final String errorMessage, final Logger log) {
        throwRuntime(new UnsupportedOperationException(), errorMessage, log);
    }

    private static void throwRuntime(final RuntimeException e, final String errorMessage, final Logger log) {
        log.error(errorMessage, e);
        throw e;
    }

}
