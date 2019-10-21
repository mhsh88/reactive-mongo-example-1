package ir.sharifi.reactivemongoexample1;

import ir.sharifi.reactivemongoexample1.model.Employee;
import ir.sharifi.reactivemongoexample1.repository.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@Log4j2
@SpringBootApplication
public class ReactiveMongoExample1Application {

	@Bean
	CommandLineRunner employee(EmployeeRepository employeeRepository){

		return args -> {
			employeeRepository.deleteAll().subscribe(null,null,()->{
				Stream.of(
						new Employee(UUID.randomUUID().toString(),"hossein",100000L),
						new Employee(UUID.randomUUID().toString(),"hassan",20000L),
						new Employee(UUID.randomUUID().toString(),"mohsen",346670L),
						new Employee(UUID.randomUUID().toString(),"amin",3456467L),
						new Employee(UUID.randomUUID().toString(),"ali",4680986L)

				).forEach(employee -> employeeRepository.save(employee).subscribe(System.out::println));
			});
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactiveMongoExample1Application.class, args);
	}

}
