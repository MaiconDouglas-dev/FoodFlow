package com.foodflow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(classes = FoodflowBackendApplication.class)
@ActiveProfiles("test")
class FoodflowBackendApplicationTests {

	@Test
	void contextLoads() {
	}
}
