package demo.service.impl;

import demo.dao.EmployeeDao;
import demo.exception.NotFoundException;
import demo.exception.NotFoundExceptionDoubleEmployee;
import demo.exception.NotFoundExceptionOutEmployee;
import demo.model.Employee;
import demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employeeList = employeeDao.findAll();
        if (employeeList.isEmpty()){
            throw new NotFoundException();
        }
        return employeeList;
    }

    @Override
    public Employee create(Employee employee) {
        String surname = employee.getSurname();
        String name = employee.getName();
        String patronymic = employee.getPatronymic();
        for (Employee employeeByList : getAll()){
            if (employeeByList.getSurname().equalsIgnoreCase(surname) &&
                employeeByList.getName().equalsIgnoreCase(name) &&
                employeeByList.getPatronymic().equalsIgnoreCase(patronymic)){
                throw new NotFoundExceptionDoubleEmployee();
            }
        }
        return employeeDao.create(employee);
    }

    @Override
    public Employee update(Employee employee) {
        int employeeId = employee.getId();
        Optional<Employee> employeeUpdate = getAll().stream()
                .filter(employeeByList -> employeeByList.getId() == employeeId)
                .findFirst();
        if (!employeeUpdate.isPresent()){
            throw new NotFoundExceptionOutEmployee();
        }
        return employeeDao.update(employee);
    }

    @Override
    public boolean deleteById(int id) {
        return getAll().stream()
                .filter(employeeByList -> employeeByList.getId() == id)
                .findFirst()
                .map(employee -> employeeDao.deleteById(id))
                .orElseThrow(() -> new NotFoundExceptionOutEmployee());
    }
}
