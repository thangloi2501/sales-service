package com.demo.sales.service;

import com.demo.sales.service.helper.OrderServiceHelper;
import com.demo.sales.db.repository.StoreOrderRepo;
import com.demo.sales.dto.order.OrderImportResult;
import com.demo.sales.exception.ApiException;
import com.demo.sales.model.StoreOrder;
import com.demo.sales.utils.AppUtils;
import com.demo.sales.utils.GlobalConstants;
import com.demo.sales.utils.mapper.StoreOrderMapper;
import com.demo.sales.utils.parser.DataParser;
import one.util.streamex.StreamEx;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Loi Nguyen
 *
 */
@Service
public class OrderService {

    /**
     * Import orders
     *
     * @param file  CSV file format to import
     * @return  import results
     */
    public OrderImportResult importOrders(MultipartFile file) throws IOException {
        Assert.notNull(file, AppUtils.getMessage("sales.order.import.error.data-file-must-be-provided"));

        if (!GlobalConstants.FileExtension.CSV.equalsIgnoreCase(FilenameUtils.getExtension(file.getOriginalFilename())))
            throw new ApiException(HttpStatus.BAD_REQUEST, AppUtils.getMessage("sales.order.import.error.import-file-must-be-csv-extension"));

        List<StoreOrder> storeOrders = Bean.csvFileParser.readData(file.getInputStream(), StoreOrder.class);

        OrderImportResult result = new OrderImportResult();

        // process rows one by one, persisting valid rows into database, ignoring invalid ones
        StreamEx.of(storeOrders)
                .forEach(storeOrder -> {
                    try {
                        OrderServiceHelper.validateOrder(storeOrder);

                        Bean.storeOrderRepo.save(Bean.storeOrderMapper.modelToEntity(storeOrder));

                        result.add(storeOrder);
                    } catch (Throwable ex) {
                        result.add(storeOrder, OrderServiceHelper.getImportThrowableMessage(ex));
                    }
                });

        return result;
    }

    @Component
    public static class Bean {
        private static StoreOrderMapper storeOrderMapper;
        private static StoreOrderRepo storeOrderRepo;
        private static DataParser csvFileParser;

        @Autowired
        protected Bean(StoreOrderMapper storeOrderMapper,
                       StoreOrderRepo storeOrderRepo,
                       @Qualifier("CommonCSVFileParser") DataParser csvFileParser) {
            Bean.storeOrderMapper = storeOrderMapper;
            Bean.storeOrderRepo   = storeOrderRepo;
            Bean.csvFileParser    = csvFileParser;
        }
    }
}
