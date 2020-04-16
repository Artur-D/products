package com.example.products.common;

import com.example.products.common.exceptions.UnreachableLine;
import com.example.products.common.util.ExceptionUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Deserializer which throws UnsupportedOperationException.
 * Protects from accidentally using partial JSON to instantiate domain object, which could be not consistent.
 * It helps with having a full control of creating internally consistent domain objects.
 */
public final class UnsupportedDeserializationJsonDeserializer extends JsonDeserializer {
    private static final Logger log = LoggerFactory.getLogger(UnsupportedDeserializationJsonDeserializer.class);

    @Override
    public Object deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        ExceptionUtils.throwUnsupportedOperation("Deserialization unsupported", log);
        throw new UnreachableLine();
    }
}