package demo.service.impl;

import demo.dao.EmployeeDao;
import demo.dto.EmployeeCreateResponse;
import demo.dto.EmployeeItemDto;
import demo.exception.DuplicateException;
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
    public List<EmployeeItemDto> getAll() {
        return employeeDao.findAll();
    }

    @Override
    public EmployeeItemDto getById(Integer id) {
        return employeeDao.findById(id)
                .orElseThrow(()-> new NotFoundException(String.format("Сотрудника таким ID: %s не нашлось в списке", id)));
    }

    @Override
    public EmployeeCreateResponse create(Employee employee) {
        String surname = employee.getSurname();
        String name = employee.getName();
        String patronymic = employee.getPatronymic();
        for (EmployeeItemDto employeeByList : getAll()){
            if (employeeByList.getSurname().equalsIgnoreCase(surname) &&
                employeeByList.getName().equalsIgnoreCase(name) &&
                employeeByList.getPatronymic().equalsIgnoreCase(patronymic)){
                throw new DuplicateException(String.format("Такой сотрудник уже существует в системе ID: %s", employeeByList.getId()));
            }
        }
        Employee createdEmployee = employeeDao.create(employee);
        return EmployeeCreateResponse.of(createdEmployee.getId());
    }

    @Override
    public void update(Employee employee) {
        employeeDao.findById(employee.getId())
                .orElseThrow(() -> new NotFoundException("Сотрудник не найден"));
        employeeDao.update(employee);
    }

    @Override
    public boolean deleteById(Integer id) {
        employeeDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Сотрудник не найден"));
        return employeeDao.deleteById(id);
    }
}
