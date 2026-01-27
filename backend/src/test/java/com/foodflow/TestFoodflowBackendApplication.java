package com.foodflow;

import org.springframework.boot.SpringApplication;

public class TestFoodflowBackendApplication {

	public static void main(String[] args) {
		SpringApplication.from(FoodflowBackendApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
