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
public enum Category {
    FURNITURE("Furniture"), OFFICE_SUPPLIES("Office Supplies"), TECHNOLOGY("Technology"), EMPTY("");

    private String value;

    public static Category fromValue(String value) {
        return StreamEx.of(Category.values())
                       .findFirst(enumValue -> enumValue.value.equalsIgnoreCase(value))
                       .orElse(Category.EMPTY);
    }

    public static String toValue(Category enumValue) {
        return enumValue == null ? null : enumValue.value;
    }
}
