package demo.dao.impl;

import demo.dao.EmployeeDao;
import demo.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private List<Employee> employeeList = new ArrayList<>();
    private int idEmployee = 1;


    public EmployeeDaoImpl(){
        create(new Employee(0, "Петров", "Юрий", "Юрьевич", "текст", "Москва", LocalDate.of(2025,01,01)));
        create(new  Employee(0, "Сидоров", "Максим", "Олегович", "текст", "Уфа", LocalDate.of(2024,02,21)));
    }

    @Override
    public List<Employee> findAll() {
        return employeeList;
    }

    @Override
    public Employee create(Employee employee) {
        employee.setId(idEmployee++);
        employee.setDate(LocalDate.now());
        employeeList.add(employee);
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        int employeeId = employee.getId();
        findAll().stream()
                .filter(employeeUp -> employeeUp.getId() == employeeId)
                .forEach(employeeUpdate -> {
                    employeeUpdate.setName(employee.getName());
                    employeeUpdate.setSurname(employee.getSurname());
                    employeeUpdate.setPatronymic(employee.getPatronymic());
                    employeeUpdate.setCityName(employee.getCityName());
                    employeeUpdate.setDate(LocalDate.now());
                });
        return employee;
    }

    @Override
    public boolean deleteById(int id) {
//        for (Employee employee : findAll()){
//            if (employee.getId() == id){
//                return findAll().remove(employee);
//            }
//        }
//        return true;
        return findAll().removeIf(employee -> employee.getId() == id);
    }
}
