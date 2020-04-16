package com.example.products.domain;

import com.example.products.common.util.BigDecimalUtils;
import com.example.products.common.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public final class ProductFactory {

    private static final Logger log = LoggerFactory.getLogger(ProductFactory.class);

    private ProductFactory() {
        ExceptionUtils.throwUnsupportedOperation("ERR_20200416155743768", log);
    }

    public static Product create(final String name, final BigDecimal price) {
        ExceptionUtils.notNull(name, "ERR_20200416162305249", log);
        ExceptionUtils.notNull(price, "ERR_20200416162753684", log);
        if (BigDecimalUtils.isLessThanZero(price)) {
            ExceptionUtils.throwIllegalArgument("ERR_20200416162803972", log);
        }

        final Product product = new Product(name, price);
        return product;
    }
}
