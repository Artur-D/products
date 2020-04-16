package com.example.products.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public final class BigDecimalUtils {
    private static final Logger log = LoggerFactory.getLogger(BigDecimalUtils.class);

    private BigDecimalUtils() {
        ExceptionUtils.throwUnsupportedOperation("ERR_20200416155743768", log);
    }

    public static boolean isLessThanZero(final BigDecimal value) {
        if (value == null)
            ExceptionUtils.throwIllegalArgument("ERR_20200416160004469", log);

        final boolean result = (value.compareTo(BigDecimal.ZERO) < 0);
        return result;
    }

}
