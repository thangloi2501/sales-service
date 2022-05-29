package com.demo.sales.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.demo.sales.utils.AppUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

/**
 * @author Loi Nguyen
 */
@Getter @Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ApiResponse<T> {
    private final String version = AppUtils.getAppVersion();
    private long timestamp;
    private T data;

    @Builder
    public ApiResponse(@Nullable T data) {
        super();
        this.timestamp = System.currentTimeMillis();
        this.data      = data;
    }
}
