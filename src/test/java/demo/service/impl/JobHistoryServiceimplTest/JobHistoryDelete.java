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
public class JobHistoryDelete {

    @Mock
    private JobHistoryDaoImpl jobHistoryDaoImpl;

    @InjectMocks
    private JobHistoryServiceImpl jobHistoryServiceImpl;

    @Test
    void deleteOutId() {
        int id = 222;
        JobHistory deleteJobHistory = new JobHistory(id, 1, 1, 1, true);

        when(jobHistoryDaoImpl.findById(id)).thenThrow(new NotFoundException(String.format("Записи с таким ID: %s не нашлось в списке", id)));

        assertThrows(NotFoundException.class, () -> {
            jobHistoryServiceImpl.deleteById(id);
        });

        verify(jobHistoryDaoImpl, never()).deleteById(any(Integer.class));
        verify(jobHistoryDaoImpl).findById(id);
    }
}
