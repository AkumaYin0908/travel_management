package gov.coateam1;

import gov.coateam1.model.employee.Driver;
import gov.coateam1.model.employee.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class  TravelManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(TravelManagementApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


	//Swagger
	//http://localhost:8010/coa/swagger-ui/index.html#/

}
