package demo.service.impl.EmployeeServiceImplTest;

import demo.dao.impl.EmployeeDaoImpl;
import demo.dto.EmployeeCreateResponse;
import demo.dto.EmployeeItemDto;
import demo.model.City;
import demo.model.Employee;
import demo.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeSuccessfullTest {

    @Mock
    private EmployeeDaoImpl employeeDaoImpl;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Test
    void getAll() {
        EmployeeItemDto employee = new EmployeeItemDto(100, "aaa", "bbb", "ccc", "test",
                new City(1, "Москва"), LocalDate.now());

        List<EmployeeItemDto> employeeList = Arrays.asList(employee);

        when(employeeDaoImpl
                .findAll())
                .thenReturn(employeeList);

        List<EmployeeItemDto> result = employeeServiceImpl.getAll();

        assertEquals(1, result.size());
        assertEquals("bbb", result.get(0).getName());

        verify(employeeDaoImpl).findAll();
    }

    @Test
    void getById() {
        int id = 111;
        EmployeeItemDto emp = new EmployeeItemDto(id, "qqq", "www", "eee", "rrr", new City(1, "Москва"), LocalDate.now());

        when(employeeDaoImpl
                .findById(id))
                .thenReturn(Optional.of(emp));

        EmployeeItemDto employee = employeeServiceImpl.getById(id);

        assertEquals(employee.getName(), "www");
    }

    @Test
    void create() {
        Employee candidateToSave = new Employee(123,"qwe", "qwe", "qwe","qwe",1, LocalDate.now());
        Employee savedCandidate = new Employee(123,"qwe", "qwe", "qwe","qwe",1, LocalDate.now());

        when(employeeDaoImpl.create(any(Employee.class))).thenReturn(savedCandidate);

        EmployeeCreateResponse result = employeeServiceImpl.create(candidateToSave);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(123);

        verify(employeeDaoImpl, times(1)).create(candidateToSave);
    }

    @Test
    void update() {
        int id = 123;
        EmployeeItemDto existingCandidate = new EmployeeItemDto(123, "que", "que", "que", "que",
                new City(1, "Москва"), LocalDate.now());
        Employee updateDetails = new Employee(123, "www", "www", "www", "www", 2, LocalDate.now());

        when(employeeDaoImpl.findById(id)).thenReturn(Optional.of(existingCandidate));
        doNothing().when(employeeDaoImpl).update(any(Employee.class));

        employeeServiceImpl.update(updateDetails);

        verify(employeeDaoImpl).findById(id);

        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeDaoImpl).update(captor.capture());
        Employee capturedEmployee = captor.getValue();

        assertThat(capturedEmployee.getName()).isEqualTo("www");
        assertThat(capturedEmployee.getSurname()).isEqualTo("www");
        assertThat(capturedEmployee.getPatronymic()).isEqualTo("www");
        assertThat(capturedEmployee.getDescription()).isEqualTo("www");
        assertThat(capturedEmployee.getCityId()).isEqualTo(2);
    }

    @Test
    void deleteById() {
        int id = 123;
        EmployeeItemDto deleteEmp = new EmployeeItemDto(123,"qwe", "qwe", "qwe","qwe",
                new City(1, "Москва"), LocalDate.now());

        when(employeeDaoImpl.findById(id)).thenReturn(Optional.of(deleteEmp));
        employeeServiceImpl.deleteById(id);

        verify(employeeDaoImpl, times(1)).deleteById(id);
    }
}