package com.hvn.productservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
class ProductServiceApplicationTests {

	@Container
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0.31");

	@Test
	void contextLoads() {
	}

}
