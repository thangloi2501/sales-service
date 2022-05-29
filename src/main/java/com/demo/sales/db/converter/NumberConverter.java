package com.demo.sales.db.converter;

import lombok.experimental.UtilityClass;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Loi Nguyen
 *
 */
@UtilityClass
public class NumberConverter {
    @Converter
    public static class IntegerToPrimitiveConverter implements AttributeConverter<Integer, Integer> {
        @Override
        public Integer convertToEntityAttribute(Integer dbData) {
            return dbData != null ? dbData : 0;
        }

        @Override
        public Integer convertToDatabaseColumn(Integer value) {
            return value == null ? 0 : value;
        }
    }

    @Converter
    public static class DoubleToPrimitiveConverter implements AttributeConverter<Double, Double> {
        @Override
        public Double convertToEntityAttribute(Double dbData) {
            return dbData != null ? dbData : 0;
        }

        @Override
        public Double convertToDatabaseColumn(Double value) {
            return value == null ? 0 : value;
        }
    }

    @Converter
    public static class FloatToPrimitiveConverter implements AttributeConverter<Float, Float> {
        @Override
        public Float convertToEntityAttribute(Float dbData) {
            return dbData != null ? dbData : 0;
        }

        @Override
        public Float convertToDatabaseColumn(Float value) {
            return value == null ? 0 : value;
        }
    }
}