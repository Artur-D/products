package com.example.products.domain.jackson;

import com.example.products.common.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DomainEntityViews {

    private static final Logger log = LoggerFactory.getLogger(DomainEntityViews.class);

    private DomainEntityViews() {
        ExceptionUtils.throwUnsupportedOperation("ERR_20200416212836061", log);
    }

    public interface ValueObjects extends Id, CreatedAt {
    }

    public interface Id {
    }

    public interface CreatedAt {
    }
}