package com.auxiongg.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class RestaurantApplicationTests {

    @Test
    void contextLoads() {
        assertTrue(true);
    }

}
