package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "sfg.customer", ignoreInvalidFields = false)
public class CustomerClient {

    private String apiHost;

    private String CUSTOMER_PATH_V1 = "/api/v1/customer";

    @PostConstruct
    private void onPostConstruct() {
        CUSTOMER_PATH_V1 = apiHost + CUSTOMER_PATH_V1;
    }


    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        System.out.println(restTemplateBuilder);
        this.restTemplate = restTemplateBuilder.build();
    }

    public CustomerDto getCustomerById(UUID uuid) {
        String url = String.format("%s/%s", CUSTOMER_PATH_V1, uuid.toString());
        return this.restTemplate.getForObject(url, CustomerDto.class);
    }

    public CustomerDto addNewCustomer(CustomerDto customerDto) {
        return this.restTemplate.postForObject(CUSTOMER_PATH_V1, customerDto, CustomerDto.class);

    }


    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public void updateCustomer(CustomerDto customerDto) {
        String url =String.format("%s", CUSTOMER_PATH_V1);
        this.restTemplate.put(url, customerDto);
    }

    public void deleteCustomer(UUID uuid) {
        String url =String.format("%s/%s", CUSTOMER_PATH_V1, uuid);
        this.restTemplate.delete(url);

    }
}
