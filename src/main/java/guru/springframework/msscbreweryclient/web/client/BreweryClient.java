package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "sfg.brewery", ignoreInvalidFields = false)
public class BreweryClient {

    public final String BEER_PATH_V1 = "/api/v1/beer";
    private String apiHost;
    public final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID id) {
        String url = String.format("%s%s%s", apiHost, BEER_PATH_V1, id.toString());
        System.out.println(url);
        return restTemplate.getForObject(url, BeerDto.class);
    }

    public URI saveNewBeer(BeerDto dto) {
        String url = String.format("%s%s", apiHost, BEER_PATH_V1);

        return restTemplate.postForLocation(url, dto, BeerDto.class);
    }

    public void updateBeer(BeerDto dto) {
        String url = String.format("%s%s/%s", apiHost, BEER_PATH_V1, dto.getId());

        restTemplate.put(url, dto);
    }

    public void deleteBeer(UUID id) {
        String url = String.format("%s%s/%s", apiHost, BEER_PATH_V1, id);

        restTemplate.delete(url);
    }


    public String getApiHost() {
        return apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }
}
