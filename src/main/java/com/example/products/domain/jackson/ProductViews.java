package com.example.products.domain.jackson;

import com.example.products.common.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ProductViews  {

    private static final Logger log = LoggerFactory.getLogger(ProductViews.class);

    private ProductViews() {
        ExceptionUtils.throwUnsupportedOperation("ERR_20200416212350567", log);
    }

    public interface ValueObjects extends DomainEntityViews.ValueObjects, Name, Price, Status {}

    public interface Name {}

    public interface Price {}

    public interface Status {
    }
}