package demo.service;

import demo.dto.EmployeeCreateResponse;
import demo.dto.EmployeeItemDto;
import demo.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<EmployeeItemDto> getAll();

    EmployeeItemDto getById (Integer id);

    EmployeeCreateResponse create (Employee employee);

    void update (Employee employee);

    boolean deleteById (Integer id);
}
