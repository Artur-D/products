package com.example.products.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public final class DtoUtils {
    private static final Logger log = LoggerFactory.getLogger(DtoUtils.class);

    private DtoUtils() {
        ExceptionUtils.throwUnsupportedOperation("ERR_20200416131812730", log);
    }

    public static boolean isNotNullAndPresent(final Optional value) {
        final boolean result = (value != null && value.isPresent());
        return result;
    }

}
