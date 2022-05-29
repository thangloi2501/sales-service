package com.demo.sales.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import one.util.streamex.StreamEx;

/**
 * @author Loi Nguyen
 *
 */
@Getter
@AllArgsConstructor
public enum ShipMode {
    STANDARD_CLASS("Standard Class"), SECOND_CLASS("Second Class"), EMPTY("");

    private String value;

    public static ShipMode fromValue(String value) {
        return StreamEx.of(ShipMode.values())
                       .findFirst(enumValue -> enumValue.value.equalsIgnoreCase(value))
                       .orElse(ShipMode.EMPTY);
    }

    public static String toValue(ShipMode enumValue) {
        return enumValue == null ? null : enumValue.value;
    }
}
