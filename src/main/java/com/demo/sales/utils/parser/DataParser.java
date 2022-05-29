package com.demo.sales.utils.parser;

import java.io.InputStream;
import java.util.List;

/**
 * @author Loi Nguyen
 *
 */
public interface DataParser {
    <T> List<T> readData(InputStream inputStream, Class<T> objectType);
}
