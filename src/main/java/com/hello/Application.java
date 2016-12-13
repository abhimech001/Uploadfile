package com.hello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import com.hello.storage.StorageProperties;
import com.hello.storage.StorageService;

@EnableAsync
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		// String str = "digital store acc";
		// String[] splited = str.split(" ");
		// Arrays.asList(splited);
		//
		// List<String> li = new ArrayList<String>();
		// li.add("apps");
		// li.add("digital");
		//
		// String res = "";
		// for (String i : splited) {
		// if (!li.contains(i)) {
		// res = res + " " + i;
		// }
		// }
		// System.out.println(res);
	}

	// @Bean
	// CommandLineRunner init(StorageService storageService) {
	// return (args) -> {
	//// storageService.deleteAll();
	//// storageService.init();
	// };
	// }

}
