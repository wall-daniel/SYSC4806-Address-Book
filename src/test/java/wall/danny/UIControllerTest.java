package wall.danny;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import wall.danny.UIController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UIControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UIController controller;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void indexPageLoads() {
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/", String.class))
                .contains("AddressBooks JQuery");
    }
}