package com.example.fukushi;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled("DB接続が必要なためCIのみで行う")
@SpringBootTest
class FukushiInventoryApplicationTests {

    @Test
    void contextLoads() {
    }
}
