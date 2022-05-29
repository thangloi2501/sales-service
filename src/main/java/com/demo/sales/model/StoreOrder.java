package com.demo.sales.model;

import com.demo.sales.enums.Category;
import com.demo.sales.enums.Segment;
import com.demo.sales.enums.ShipMode;
import com.demo.sales.enums.SubCategory;
import com.demo.sales.utils.parser.csv.converter.CategoryConverter;
import com.demo.sales.utils.parser.csv.converter.LocalDateConverter;
import com.demo.sales.utils.parser.csv.converter.SegmentConverter;
import com.demo.sales.utils.parser.csv.converter.ShipModeConverter;
import com.demo.sales.utils.parser.csv.converter.SubCategoryConverter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * @author Loi Nguyen
 *
 */
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor @Builder
public class StoreOrder {
    @CsvBindByName(column = "Row ID")
    @NotNull(message = "{sales.model.field.must-be-provided}")
    private Integer rowId;

    @CsvBindByName(column = "Order ID")
    @NotEmpty(message = "{sales.model.field.must-be-provided}")
    @Size(max=20)
    private String orderId;

    @CsvCustomBindByName(column = "Order Date", converter = LocalDateConverter.class)
    @NotNull(message = "{sales.model.field.invalid-date}")
    private LocalDate orderDate;

    @CsvCustomBindByName(column = "Ship Date", converter = LocalDateConverter.class)
    @NotNull(message = "{sales.model.field.invalid-date}")
    private LocalDate shipDate;

    @CsvCustomBindByName(column = "Ship Mode", converter = ShipModeConverter.class)
    private ShipMode shipMode;

    @CsvBindByName(column = "Customer ID")
    @NotEmpty(message = "{sales.model.field.must-be-provided}")
    @Size(max=20)
    private String customerId;

    @CsvBindByName(column = "Customer Name")
    @NotEmpty(message = "{sales.model.field.must-be-provided}")
    @Size(max=255)
    private String customerName;

    @CsvCustomBindByName(column = "Segment", converter = SegmentConverter.class)
    private Segment segment;

    @CsvBindByName(column = "Country")
    private String country;

    @CsvBindByName(column = "City")
    private String city;

    @CsvBindByName(column = "State")
    private String state;

    @CsvBindByName(column = "Postal Code")
    private String postalCode;

    @CsvBindByName(column = "Region")
    private String region;

    @CsvBindByName(column = "Product ID")
    @NotEmpty(message = "{sales.model.field.must-be-provided}")
    @Size(max=20)
    private String productId;

    @CsvCustomBindByName(column = "Category", converter = CategoryConverter.class)
    @NotNull(message = "{sales.model.field.must-be-provided}")
    private Category category;

    @CsvCustomBindByName(column = "Sub-Category", converter = SubCategoryConverter.class)
    private SubCategory subCategory;

    @CsvBindByName(column = "Product Name")
    @Size(max=255)
    private String productName;

    @CsvBindByName(column = "Sales")
    private Double sales;

    @CsvBindByName(column = "Quantity")
    @NotNull(message = "{sales.model.field.must-be-provided}")
    private Integer quantity;

    @CsvBindByName(column = "Discount")
    private Double discount;

    @CsvBindByName(column = "Profit")
    @NotNull(message = "{sales.model.field.must-be-provided}")
    private Double profit;
}
