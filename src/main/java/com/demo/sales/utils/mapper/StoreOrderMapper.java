package com.demo.sales.utils.mapper;

import com.demo.sales.db.entity.StoreOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoreOrderMapper {
    @Mapping(source = "model.orderId", target = "orderId")
    @Mapping(source = "model.orderDate", target = "orderDate")
    @Mapping(source = "model.shipDate", target = "shipDate")
    @Mapping(source = "model.shipMode", target = "shipMode")
    @Mapping(source = "model.quantity", target = "quantity")
    @Mapping(source = "model.discount", target = "discount")
    @Mapping(source = "model.profit", target = "profit")
    @Mapping(source = "model.productId", target = "productId")
    @Mapping(source = "model.customerName", target = "customerName")
    @Mapping(source = "model.category", target = "category")
    @Mapping(source = "model.customerId", target = "customerId")
    @Mapping(source = "model.productName", target = "productName")
    StoreOrder modelToEntity(com.demo.sales.model.StoreOrder model);

    com.demo.sales.model.StoreOrder entityToModel(StoreOrder entity);
}