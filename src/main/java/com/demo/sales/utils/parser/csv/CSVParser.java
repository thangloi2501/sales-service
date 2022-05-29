package com.demo.sales.utils.parser.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import com.demo.sales.utils.parser.DataParser;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author Loi Nguyen
 *
 */
@Component("CommonCSVFileParser")
public class CSVParser implements DataParser {
    @Override
    public <T> List<T> readData(InputStream inputStream, Class<T> objectType) {
        return new CsvToBeanBuilder(new InputStreamReader(inputStream)).withType(objectType)
                                                                       .build()
                                                                       .parse();
    }
}
