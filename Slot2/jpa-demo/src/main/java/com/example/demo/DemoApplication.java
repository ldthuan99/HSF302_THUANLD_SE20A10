package com.example.demo;

import com.example.demo.Service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(StudentService service) {
		return args -> {
			service.createStudent("Nguyễn Văn A", "a@fpt.edu.vn", 20);
			service.createStudent("Trần Thị B", "b@fpt.edu.vn", 21);
			service.printAll();

			//test update student với id=1
			service.updateStudent(1L,"LDT","ldt99",22);
			System.out.println("After update: ");
			service.printAll();

		};
	}
}
