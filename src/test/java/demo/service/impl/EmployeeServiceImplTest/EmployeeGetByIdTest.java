package demo.service.impl.EmployeeServiceImplTest;

import demo.dao.impl.EmployeeDaoImpl;
import demo.exception.NotFoundException;
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
public class EmployeeGetByIdTest {

    @Mock
    private EmployeeDaoImpl employeeDaoImpl;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Test
    void getByIdOut() {
        int id = 222;

        when(employeeDaoImpl.findById(id))
                .thenThrow(new NotFoundException(String.format("Сотрудника таким ID: %s не нашлось в списке", id)));

        assertThrows(NotFoundException.class, () -> {
            employeeServiceImpl.getById(id);
        });

        verify(employeeDaoImpl).findById(id);
    }
}
