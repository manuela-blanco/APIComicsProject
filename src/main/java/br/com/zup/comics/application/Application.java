package br.com.zup.comics.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableJpaRepositories("br.com.zup.comics.repository")
@EntityScan("br.com.zup.comics.entity")
@ComponentScan(basePackages = "br.com.zup.comics")
public class Application {
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public RestTemplate buildRestTemplate() {
        return new RestTemplate();
    }
}

