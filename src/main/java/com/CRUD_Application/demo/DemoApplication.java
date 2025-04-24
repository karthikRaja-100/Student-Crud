package com.CRUD_Application.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(DataSource dataSource) {
		return args -> {
			try (Connection connection = dataSource.getConnection()) {
//				String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS mydatabase";


				try (Statement statement = connection.createStatement()) {
//					statement.execute(createDatabaseQuery);

				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error creating database.");
			}
		};
	}
}
