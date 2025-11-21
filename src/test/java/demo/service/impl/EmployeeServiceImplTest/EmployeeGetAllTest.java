package demo.service.impl.EmployeeServiceImplTest;

import demo.dao.EmployeeDao;
import demo.dao.impl.EmployeeDaoImpl;
import demo.dto.EmployeeItemDto;
import demo.model.Employee;
import demo.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeGetAllTest {

    @Mock
    private EmployeeDaoImpl employeeDaoImpl;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Test
    void getAllIsEmpty() {
        List<EmployeeItemDto> employeeList = new ArrayList<>();

        when(employeeDaoImpl
                .findAll())
                .thenReturn(employeeList);

        List<EmployeeItemDto> result = employeeServiceImpl.getAll();
        assertThat(result).isEmpty();

        verify(employeeDaoImpl).findAll();
    }
}
