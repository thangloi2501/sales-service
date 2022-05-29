package com.demo.sales.dto.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.demo.sales.model.StoreOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Loi Nguyen
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class OrderImportResult {
    private List<RowImportResult> successRows = new ArrayList<>();
    private List<RowImportResult> errorRows   = new ArrayList<>();

    public void add(StoreOrder order, Optional<String> error) {
        if (error.isPresent())
            this.add(order, error.get());
        else
            this.add(order);
    }

    public void add(StoreOrder order) {
        this.successRows.add(RowImportResult.builder()
                                            .rowId(order.getRowId())
                                            .orderId(order.getOrderId())
                                            .build());
    }

    public void add(StoreOrder order, String error) {
        this.errorRows.add(RowImportResult.builder()
                                          .rowId(order.getRowId())
                                          .orderId(order.getOrderId())
                                          .error(error)
                                          .build());
    }

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor @Builder
    public static class RowImportResult {
        private Integer rowId;
        private String orderId;
        @JsonInclude(Include.NON_EMPTY)
        private String error;

        @Override
        public int hashCode() {
            return Objects.hash(this.rowId, this.orderId, this.error);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other)
                return true;

            if (!(other instanceof RowImportResult))
                return false;

            RowImportResult that = (RowImportResult) other;
            return Objects.equals(that.rowId, this.rowId)
                    && Objects.equals(that.orderId, this.orderId)
                    && Objects.equals(that.error, this.error);
        }
    }
}
