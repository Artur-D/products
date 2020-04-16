package com.example.products.domain;


import com.example.products.common.UnsupportedDeserializationJsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = UnsupportedDeserializationJsonDeserializer.class)
public interface DomainObjectConsistentable {
}
