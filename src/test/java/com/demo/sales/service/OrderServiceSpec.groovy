package com.demo.sales.service


import com.demo.sales.config.TestEnvironmentConfiguration
import com.demo.sales.db.repository.StoreOrderRepo
import com.demo.sales.dto.order.OrderImportResult
import com.demo.sales.enums.Category
import com.demo.sales.enums.Segment
import com.demo.sales.enums.ShipMode
import com.demo.sales.enums.SubCategory
import com.demo.sales.exception.ApiException
import com.demo.sales.model.StoreOrder
import com.demo.sales.utils.mapper.StoreOrderMapper
import com.demo.sales.utils.parser.DataParser
import org.spockframework.spring.SpringBean
import org.spockframework.spring.SpringSpy
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.PropertySource
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.util.ReflectionTestUtils
import spock.lang.Specification

import java.time.LocalDate

@ContextConfiguration(classes = [TestEnvironmentConfiguration.class, OrderService.class])
@PropertySource(value="classpath:application.yaml")
class OrderServiceSpec extends Specification {
    @SpringSpy
    OrderService orderService

    @SpringBean
    StoreOrderMapper storeOrderMapper = Mock()
    @SpringBean
    StoreOrderRepo storeOrderRepo = Mock()
    @SpringBean
    @Qualifier("CommonCSVFileParser")
    DataParser csvFileParser = Mock()

    def setup() {
        ReflectionTestUtils.setField(OrderService.Bean.class, "storeOrderMapper", storeOrderMapper)
        ReflectionTestUtils.setField(OrderService.Bean.class, "storeOrderRepo", storeOrderRepo)
        ReflectionTestUtils.setField(OrderService.Bean.class, "csvFileParser", csvFileParser)
    }

    def "Import orders should throw exception if file is null"() {
        when:
        orderService.importOrders(null)

        then: 'exception expected'
        IllegalArgumentException ex  =  thrown()
        ex.message                   == "sales.order.import.error.data-file-must-be-provided"
    }

    def "Import orders should throw exception if file is not csv file"() {
        given:
        MockMultipartFile file = new MockMultipartFile("sales.txt", "sales.txt",
                                                       "text", "file content...".getBytes())

        when:
        orderService.importOrders(file)

        then: 'exception expected'
        ApiException ex  =  thrown()
        ex.status        == HttpStatus.BAD_REQUEST
        ex.message       == "sales.order.import.error.import-file-must-be-csv-extension"
    }

    def "Import orders should succeed"() {
        given:
        String fileContent = '1,CA-2015-117415,27.12.15,31.12.15,Standard Class,SN-20710,Steve Nguyen,Home Office,United States,Houston,Texas,77041,Central,OFF-EN-10002986,Office Supplies,Envelopes,"#10-4 1/8"" x, 9 1/2"" Premium Diagonal Seam Envelopes",113.328,9,0.2,35.415\n' +
                '2,,27.12.15,31.12.15,Standard Class,SN-20711,Steve Nguyen,Home Office,United States,Houston,Texas,77041,Central,,Office Supplies,Envelopes,"#10-4 1/8"" x, 9 1/2"" Premium Diagonal Seam Envelopes",113.328,9,0.2,35.415'
        MockMultipartFile file = new MockMultipartFile("sales.csv", "sales.csv",
                                                       "text/csv", fileContent.getBytes())

        def successRow = [
                rowId: 1,
                orderId: 'CA-2015-117415'
        ] as OrderImportResult.RowImportResult

        def errorRow = [
                rowId: 2,
                orderId: '',
                error: 'ValidationError(name=orderId, message=sales.model.field.must-be-provided). ValidationError(name=productId, message=sales.model.field.must-be-provided)'
        ] as OrderImportResult.RowImportResult

        def expected = new OrderImportResult()
        expected.successRows = [successRow]
        expected.errorRows = [errorRow]

        def order1 = [
                rowId       : 1,
                orderId     : 'CA-2015-117415',
                orderDate   : LocalDate.of(2015, 12, 27),
                shipDate    : LocalDate.of(2015, 12, 31),
                shipMode    : ShipMode.STANDARD_CLASS,
                customerId  : 'SN-20710',
                customerName: 'Steve Nguyen',
                segment     : Segment.HOME_OFFICE,
                country     : 'United States',
                city        : 'Houston',
                state       : 'Texas',
                postalCode  : '77041',
                region      : 'Central',
                productId   : 'OFF-EN-10002986',
                category    : Category.OFFICE_SUPPLIES,
                subCategory : SubCategory.ENVELOPES,
                productName : '#10-4 1/8" x, 9 1/2" Premium Diagonal Seam Envelopes',
                sales       : 113.328,
                quantity    : 9,
                discount    : 0.2,
                profit      : 35.415
        ] as StoreOrder

        def order2 = [
                rowId       : 2,
                orderId     : '',
                orderDate   : LocalDate.of(2015, 12, 27),
                shipDate    : LocalDate.of(2015, 12, 31),
                shipMode    : ShipMode.STANDARD_CLASS,
                customerId  : 'SN-20711',
                customerName: 'Steve Nguyen',
                segment     : Segment.HOME_OFFICE,
                country     : 'United States',
                city        : 'Houston',
                state       : 'Texas',
                postalCode  : '77041',
                region      : 'Central',
                productId   : '',
                category    : Category.OFFICE_SUPPLIES,
                subCategory : SubCategory.ENVELOPES,
                productName : '#10-4 1/8" x, 9 1/2" Premium Diagonal Seam Envelopes',
                sales       : 113.328,
                quantity    : 9,
                discount    : 0.2,
                profit      : 35.415
        ] as StoreOrder

        when:
        OrderImportResult result = orderService.importOrders(file)

        then:
        1 * csvFileParser.readData(_, StoreOrder.class) >> [order1, order2]
        1 * storeOrderRepo.save(_)

        then: 'result as expected'
        result.successRows.size() == 1
        result.successRows[0]     == expected.successRows[0]
        result.errorRows.size()   == 1
        result.errorRows[0]       == expected.errorRows[0]
    }
}
