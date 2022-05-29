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
public enum SubCategory {
    BOOKCASES("Bookcases"), CHAIRS("Chairs"), LABELS("Labels"), TABLES("Tables"),
    STORAGE("Storage"), FURNISHINGS("Furnishings"), ART("Art"), PHONES("Phones"),
    ENVELOPES("Envelopes"), APPLIANCES("Appliances"), EMPTY("");

    private String value;

    public static SubCategory fromValue(String value) {
        return StreamEx.of(SubCategory.values())
                       .findFirst(enumValue -> enumValue.value.equalsIgnoreCase(value))
                       .orElse(SubCategory.EMPTY);
    }

    public static String toValue(SubCategory enumValue) {
        return enumValue == null ? null : enumValue.value;
    }
}
