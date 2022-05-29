package com.demo.sales.db.converter;

import com.demo.sales.enums.Category;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Loi Nguyen
 *
 */
@Converter
public class CategoryConverter implements AttributeConverter<Category, String> {
    @Override
    public String convertToDatabaseColumn(Category value) {
        return Category.toValue(value);
    }

    @Override
    public Category convertToEntityAttribute(String value) {
        return Category.fromValue(value);
    }
}
