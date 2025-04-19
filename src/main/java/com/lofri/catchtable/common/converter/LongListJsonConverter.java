package com.lofri.catchtable.common.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Converter
@RequiredArgsConstructor
public class LongListJsonConverter
        implements AttributeConverter<List<Long>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Long> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "[]";
        }
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to convert List<Long> to JSON", e);
        }
    }

    @Override
    public List<Long> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(dbData, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to convert JSON to List<Long>", e);
        }
    }
}
