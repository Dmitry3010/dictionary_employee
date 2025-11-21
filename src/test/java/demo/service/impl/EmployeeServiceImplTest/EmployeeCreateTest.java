package demo.service.impl.EmployeeServiceImplTest;

import demo.dao.impl.EmployeeDaoImpl;
import demo.exception.DuplicateException;
import demo.model.Employee;
import demo.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeCreateTest {

    @Mock
    private EmployeeDaoImpl employeeDaoImpl;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Test
    void createDuplicateEmployee() {
        int id = 1;
        Employee candidateToSave = new Employee("qwe", "qwe", "qwe","qwe",1);

        when(employeeDaoImpl.create(any(Employee.class)))
                .thenThrow(new DuplicateException(String.format("Такой сотрудник уже существует в системе ID: %s", id)));

        assertThrows(DuplicateException.class, () -> {
            employeeServiceImpl.create(candidateToSave);
        });

        verify(employeeDaoImpl).create(candidateToSave);
    }
}
