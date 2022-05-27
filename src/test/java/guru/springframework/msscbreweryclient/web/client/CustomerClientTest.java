package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerClientTest {
    @Autowired
    CustomerClient customerClient;


    @Test
    void getCustomerById() {
        CustomerDto customer = customerClient.getCustomerById(UUID.fromString("a6824e6f-57d6-4e0f-9227-aa2e43a876d9"));
        assertEquals("AAA", customer.getName());
    }

    @Test
    void addNewCustomer() {
        CustomerDto newCustomer = CustomerDto.builder()
                .name("AAA")
                .build();
        CustomerDto addedCustomer = customerClient.addNewCustomer(newCustomer);

        assertNotNull(addedCustomer.getId());
        assertEquals(newCustomer.getName(), addedCustomer.getName());
    }

    @Test
    void updateCustomer() {
        CustomerDto newCustomer = CustomerDto.builder()
                .name("TEST")
                .build();
        CustomerDto addedCustomer = customerClient.addNewCustomer(newCustomer);
        addedCustomer.setName("TEST2");
        customerClient.updateCustomer(addedCustomer);

        assertEquals("TEST2", customerClient.getCustomerById(addedCustomer.getId()).getName());
    }

    @Test
    void deleteCustomer() {
        CustomerDto newCustomer = customerClient.addNewCustomer(CustomerDto.builder()
                .name("TEST")
                .build());
        customerClient.deleteCustomer(newCustomer.getId());

        assertThrows(RuntimeException.class, () -> customerClient.getCustomerById(newCustomer.getId()));


    }

}