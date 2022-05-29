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
public enum Segment {
    CONSUMER("Consumer"), CORPORATE("Corporate"), HOME_OFFICE("Home Office"), EMPTY("");

    private String value;

    public static Segment fromValue(String value) {
        return StreamEx.of(Segment.values())
                       .findFirst(enumValue -> enumValue.value.equalsIgnoreCase(value))
                       .orElse(Segment.EMPTY);
    }

    public static String toValue(Segment enumValue) {
        return enumValue == null ? null : enumValue.value;
    }
}
