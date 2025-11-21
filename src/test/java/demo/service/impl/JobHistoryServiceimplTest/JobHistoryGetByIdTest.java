package demo.service.impl.JobHistoryServiceimplTest;

import demo.dao.impl.JobHistoryDaoImpl;
import demo.exception.NotFoundException;
import demo.service.impl.JobHistoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobHistoryGetByIdTest {

    @Mock
    private JobHistoryDaoImpl jobHistoryDaoImpl;

    @InjectMocks
    private JobHistoryServiceImpl jobHistoryServiceImpl;

    @Test
    public void getByIdOut() {
        int id = 222;

        when(jobHistoryDaoImpl.findById(id))
                .thenThrow(new NotFoundException(String.format("Записи с таким ID: %s не нашлось в списке", id)));

        assertThrows(NotFoundException.class, () -> {
            jobHistoryServiceImpl.getById(id);
        });

        verify(jobHistoryDaoImpl).findById(id);
    }
}
