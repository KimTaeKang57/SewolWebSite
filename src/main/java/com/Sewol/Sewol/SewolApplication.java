package com.Sewol.Sewol;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class SewolApplication {

	@SneakyThrows
	public static void main(String[] args) {
		SpringApplication.run(SewolApplication.class, args);
	}

}
