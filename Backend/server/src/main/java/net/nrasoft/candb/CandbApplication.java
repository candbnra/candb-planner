package net.nrasoft.candb;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.dao.DataAccessException;

@SpringBootApplication
public class CandbApplication extends SpringBootServletInitializer {
	public static void main(String[] args) throws DataAccessException, IOException, ParseException {
		SpringApplication.run(CandbApplication.class, args);
	}
}
