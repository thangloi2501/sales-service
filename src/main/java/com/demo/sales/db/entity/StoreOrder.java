package com.demo.sales.db.entity;

import com.demo.sales.db.converter.CategoryConverter;
import com.demo.sales.db.converter.NumberConverter.DoubleToPrimitiveConverter;
import com.demo.sales.db.converter.ShipModeConverter;
import com.demo.sales.enums.Category;
import com.demo.sales.enums.ShipMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Loi Nguyen
 *
 */
@Entity
@Table(name = "store_order")
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class StoreOrder extends AbstractEntity {

    @Column(nullable = false, length = 20, unique = true)
    private String orderId;

    @Column(nullable = false)
    private LocalDate orderDate;

    @Column(nullable = false)
    private LocalDate shipDate;

    @Column(length = 20)
    @Convert(converter = ShipModeConverter.class)
    private ShipMode shipMode;

    @Column(nullable = false)
    private Integer quantity;

    @Column(precision = 3, scale = 2)
    @Convert(converter = DoubleToPrimitiveConverter.class)
    private double discount;

    @Column(precision = 6, scale = 2, nullable = false)
    @Convert(converter = DoubleToPrimitiveConverter.class)
    private double profit;

    @Column(nullable = false, length = 20, unique = true)
    private String productId;

    @Column(nullable = false, length = 255)
    private String customerName;

    @Column(length = 255)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @Column(nullable = false, length = 20, unique = true)
    private String customerId;

    @Column(length = 255)
    private String productName;

    @Override
    public int hashCode() {
        return Objects.hashCode(this.orderId);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;

        if (!(other instanceof StoreOrder))
            return false;

        StoreOrder that = (StoreOrder) other;
        return Objects.equals(that.orderId, this.orderId);
    }
}
