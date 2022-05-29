package com.demo.sales.utils.parser.csv.converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.demo.sales.utils.AppUtils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class LocalDateConverter extends AbstractBeanField {
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        LocalDate obj = null;

        try {
            obj = LocalDate.parse(value, AppUtils.localDateFormatter());
        } catch (DateTimeParseException ex) {
            // ignore the value
        }

        return obj;
    }
}