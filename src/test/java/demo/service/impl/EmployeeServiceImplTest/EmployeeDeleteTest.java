package demo.service.impl.EmployeeServiceImplTest;

import demo.dao.impl.EmployeeDaoImpl;
import demo.exception.NotFoundException;
import demo.model.Employee;
import demo.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class EmployeeDeleteTest {

    @Mock
    private EmployeeDaoImpl employeeDaoImpl;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Test
    void deleteOutId() {
        int id = 222;
        Employee deleteEmp = new Employee(id, "www", "www", "www","www",1, LocalDate.now());

        when(employeeDaoImpl.findById(id)).thenThrow(new NotFoundException(String.format("Записи с таким ID: %s не нашлось в списке", id)));

        assertThrows(NotFoundException.class, () -> {
            employeeServiceImpl.deleteById(id);
        });

        verify(employeeDaoImpl, never()).deleteById(any(Integer.class));
        verify(employeeDaoImpl).findById(id);
    }
}
