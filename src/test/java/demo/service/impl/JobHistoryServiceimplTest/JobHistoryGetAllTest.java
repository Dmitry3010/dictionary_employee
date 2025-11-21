package demo.service.impl.JobHistoryServiceimplTest;

import demo.dao.impl.JobHistoryDaoImpl;
import demo.dto.JobHistoryCreateResponse;
import demo.dto.JobHistoryItemDto;
import demo.model.*;
import demo.service.impl.JobHistoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JobHistoryGetAllTest {
    
    @Mock
    private JobHistoryDaoImpl jobHistoryDaoImpl;

    @InjectMocks
    private JobHistoryServiceImpl jobHistoryServiceImpl;

    @Test
    void getAllIsEmpty() {
        List<JobHistoryItemDto> jobHistoryList = new ArrayList<>();

        when(jobHistoryDaoImpl.findAll()).thenReturn(jobHistoryList);

        List<JobHistoryItemDto> result = jobHistoryServiceImpl.getAll();
        assertThat(result).isEmpty();

        verify(jobHistoryDaoImpl, times(1)).findAll();
    }
}
