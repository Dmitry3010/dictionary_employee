package demo.service;

import demo.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    Employee create (Employee employee);

    Employee update (Employee employee);

    boolean deleteById (int id);
}
