package co.edu.unicundi.proyectoSpringPrueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@SpringBootApplication
public class ProyectoSpringPruebaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoSpringPruebaApplication.class, args);
	}

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	return application.sources(ProyectoSpringPruebaApplication.class);
	}
	
}

/*
@SpringBootApplication
@EnableScheduling
public class MuvitappBackendApplication extends SpringBootServletInitializer{

 public static void main(String[] args) {
SpringApplication.run(MuvitappBackendApplication.class, args);
}
@Override
protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
return application.sources(MuvitappBackendApplication.class);
}

}
*/
