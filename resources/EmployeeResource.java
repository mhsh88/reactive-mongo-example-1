package ir.sharifi.reactivemongoexample1.resources;

import ir.sharifi.reactivemongoexample1.model.Employee;
import ir.sharifi.reactivemongoexample1.model.EmployeeEvent;
import ir.sharifi.reactivemongoexample1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.awt.*;
import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@RestController
@RequestMapping("/rest/employee")
public class EmployeeResource {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeResource(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/all")
    public Flux<Employee> getAll() {

        return employeeRepository.findAll();

    }

    @GetMapping("/{id}")
    public Mono<Employee> getById(@PathVariable("id") final String employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @GetMapping(value = "/{id}/events",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EmployeeEvent> getEvents(@PathVariable("id") final String empId) {

        return employeeRepository.findById(empId).flatMapMany(employee -> {
            Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
            Flux<EmployeeEvent> employeeEventFlux = Flux.fromStream(Stream.generate(() ->
                    new EmployeeEvent(employee, new Date())
            ));

            return Flux.zip(interval, employeeEventFlux).map(Tuple2::getT2);
        });


    }
}
