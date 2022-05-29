package com.demo.sales.db.converter;

import com.demo.sales.enums.ShipMode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Loi Nguyen
 *
 */
@Converter
public class ShipModeConverter implements AttributeConverter<ShipMode, String> {
    @Override
    public String convertToDatabaseColumn(ShipMode mode) {
        return ShipMode.toValue(mode);
    }

    @Override
    public ShipMode convertToEntityAttribute(String value) {
        return ShipMode.fromValue(value);
    }
}
