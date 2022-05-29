package com.demo.sales.controller

import com.fasterxml.jackson.databind.ObjectWriter
import com.demo.sales.config.TestEnvironmentConfiguration
import com.demo.sales.service.OrderService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.PropertySource
import org.springframework.mock.web.MockMultipartFile
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

@WebMvcTest(controllers=OrderController.class)
@ContextConfiguration(classes=[
        OrderController.class,
        TestEnvironmentConfiguration.class
])
@PropertySource(value="classpath:application.yaml")
class OrderControllerSpec extends Specification {

    @Autowired
    MockMvc mvc

    @Autowired
    ObjectWriter objectWriter

    @SpringBean
    OrderService orderService = Mock()

    def "POST import orders should succeed"() {
        given:
        MockMultipartFile file = new MockMultipartFile("sales.csv", "sales.csv",
                                                       "text/csv", "file content...".getBytes())

        MockMultipartHttpServletRequestBuilder request = MockMvcRequestBuilders.multipart("/api/v1/orders/import")
                                                                               .file("file", file.getBytes())
                                                                               .with(SecurityMockMvcRequestPostProcessors.csrf());

        when:
        ResultActions result = mvc.perform(request);

        then:
        1 * orderService.importOrders(_)
        result.andExpect(MockMvcResultMatchers.status().isOk())
    }
}
