package demo.service.impl.JobHistoryServiceimplTest;

import demo.dao.impl.JobHistoryDaoImpl;
import demo.exception.NotFoundException;
import demo.model.Employee;
import demo.model.JobHistory;
import demo.service.impl.JobHistoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JobHistoryUnpinTest {

    @Mock
    private JobHistoryDaoImpl jobHistoryDaoImpl;

    @InjectMocks
    private JobHistoryServiceImpl jobHistoryServiceImpl;

    @Test
    void unpinEmployeeOut() {
        int id = 1;
        JobHistory jobHistoryPin = new JobHistory(1, 1, 1, id, true);

        when(jobHistoryDaoImpl.unPin(jobHistoryPin))
                .thenThrow(new NotFoundException(String.format("Сотрудника с таким ID: %s не нашлось в списке", id)));

        assertThrows(NotFoundException.class, () -> {
            jobHistoryServiceImpl.unPin(jobHistoryPin);
        });

        verify(jobHistoryDaoImpl).unPin(jobHistoryPin);
    }

    @Test
    void unpinVacancyOut() {
        int id = 1;
        JobHistory jobHistoryPin = new JobHistory(1, id, 1, 1, true);

        when(jobHistoryDaoImpl.unPin(jobHistoryPin))
                .thenThrow(new NotFoundException(String.format("Вакансии с таким ID: %s не нашлось в списке", id)));

        assertThrows(NotFoundException.class, () -> {
            jobHistoryServiceImpl.unPin(jobHistoryPin);
        });

        verify(jobHistoryDaoImpl).unPin(jobHistoryPin);
    }
}
