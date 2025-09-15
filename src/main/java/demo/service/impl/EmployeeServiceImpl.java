package demo.service.impl;

import demo.dao.EmployeeDao;
import demo.exception.NotFoundException;
import demo.model.Employee;
import demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getAll() {
        return employeeDao.findAll();
    }

    @Override
    public Employee getById(int id) {
        return employeeDao.findById(id)
                .orElseThrow(()-> new NotFoundException("Сотрудника таким ID не нашлось в списке"));
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
                throw new NotFoundException("Такой сотрудник уже существует в системе");
            }
        }
        return employeeDao.create(employee);
    }

    @Override
    public Employee update(Employee employee) {
        int employeeId = employee.getId();
        Employee employeeUpdate = getById(employeeId);
        return employeeDao.update(employeeUpdate);
    }

    @Override
    public boolean deleteById(int id) {
        Employee employeeDelete = getById(id);
        int employeeDeleteId = employeeDelete.getId();
        return employeeDao.deleteById(employeeDeleteId);
    }
}
