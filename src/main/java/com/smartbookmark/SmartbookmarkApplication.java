package com.smartbookmark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import(BookmarkDBConfiguration.class)
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SmartbookmarkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartbookmarkApplication.class, args);
	}
}
