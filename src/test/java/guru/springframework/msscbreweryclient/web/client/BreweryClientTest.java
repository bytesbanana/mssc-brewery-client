package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;

    @Test
    void breweryClientNotNull() {
        assertNotEquals(null, client);
    }

    @Test
    void getBeerById() {
        BeerDto dto = client.getBeerById(UUID.randomUUID());

        assertNotNull(dto);
        assertEquals("LEO", dto.getBeerName());
        assertEquals("Lager", dto.getBeerStyle());
    }

    @Test
    void saveNewBeer() {
        UUID newId = UUID.randomUUID();
        BeerDto dto = BeerDto.builder()
                .id(newId)
                .beerName("TEST")
                .beerStyle("STYLE")
                .build();

        URI uri = client.saveNewBeer(dto);

        assertEquals("/api/v1/beer/" + newId.toString(), uri.getPath());
    }

    @Test
    void updateBeer() {
        UUID newId = UUID.randomUUID();
        BeerDto dto = BeerDto.builder()
                .id(newId)
                .beerName("TEST")
                .beerStyle("STYLE")
                .build();
        client.updateBeer(dto);
    }

    @Test
    void deleteBeer() {
        client.deleteBeer(UUID.randomUUID());
    }



}