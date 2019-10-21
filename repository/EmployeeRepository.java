package ir.sharifi.reactivemongoexample1.repository;

import ir.sharifi.reactivemongoexample1.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {
}
