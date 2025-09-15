package demo.service;

import demo.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAll();

    Employee getById (int id);

    Employee create (Employee employee);

    Employee update (Employee employee);

    boolean deleteById (int id);
}
