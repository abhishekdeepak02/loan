package com.lazybytes.loan;

import com.lazybytes.loan.dto.LoanInfoRecordDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
        info = @Info(title = "Loan Microservice REST API documentation",
                description = "LazyCoder Loan Microservice REST API documentation",
                version = "v1",
                contact = @Contact(
                        name = "Abhishek",
                        email = "learning@lazycoder.com",
                        url = "http://www.lazycoder.com")
        ),
        externalDocs = @ExternalDocumentation(
                description = "LazyCoder Loan Microservice Wiki Documentation",
                url = "http://www.lazycoder.com/wiki/loan"
        )
)
@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(value = {LoanInfoRecordDto.class})
public class LoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
	}

}
