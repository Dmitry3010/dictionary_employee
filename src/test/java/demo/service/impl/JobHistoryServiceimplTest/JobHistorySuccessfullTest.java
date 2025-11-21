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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JobHistorySuccessfullTest {

    @Mock
    private JobHistoryDaoImpl jobHistoryDaoImpl;

    @InjectMocks
    private JobHistoryServiceImpl jobHistoryServiceImpl;

    @Test
    void getAll() {
        JobHistoryItemDto jobHistory = new JobHistoryItemDto(1,
                new Vacancy(1, "a", "a", 1, true, LocalDate.now()),
                new HrEmployee(1, "s", "s", "s"),
                new Employee(1, "d", "d", "d", "d", 1, LocalDate.now()),
                true);

        List<JobHistoryItemDto> jobHistoryList = Arrays.asList(jobHistory);

        when(jobHistoryDaoImpl
                .findAll())
                .thenReturn(jobHistoryList);

        List<JobHistoryItemDto> result = jobHistoryServiceImpl.getAll();

        assertEquals(1, result.size());
        assertEquals("d", result.get(0).getEmployee().getName());

        verify(jobHistoryDaoImpl).findAll();
    }

    @Test
    void getAllIsActive() {
        JobHistoryItemDto jobHistory1 = new JobHistoryItemDto(1,
                new Vacancy(1, "a", "a", 1, true, LocalDate.now()),
                new HrEmployee(1, "s", "s", "s"),
                new Employee(1, "d", "d", "d", "d", 1, LocalDate.now()),
                true);

        JobHistoryItemDto jobHistory2 = new JobHistoryItemDto(1,
                new Vacancy(2, "af", "af", 1, false, LocalDate.now()),
                new HrEmployee(1, "sf", "sf", "sf"),
                new Employee(2, "df", "df", "df", "df", 1, LocalDate.now()),
                true);

        List<JobHistoryItemDto> jobHistoryList = Arrays.asList(jobHistory1, jobHistory2);
        when(jobHistoryDaoImpl
                .findAllIsActive())
                .thenReturn(jobHistoryList);

        List<JobHistoryItemDto> result = jobHistoryServiceImpl.getAllIsActive();

        assertEquals(2, result.size());
        assertEquals("d", result.get(0).getEmployee().getName());
        assertEquals(true, result.get(0).getVacancy().isAccessibility());

        verify(jobHistoryDaoImpl).findAllIsActive();
    }

    @Test
    void getById() {
        JobHistoryItemDto jobHistory = new JobHistoryItemDto(1,
                new Vacancy(1, "a", "a", 1, true, LocalDate.now()),
                new HrEmployee(1, "s", "s", "s"),
                new Employee(1, "d", "d", "d", "d", 1, LocalDate.now()),
                true);

        when(jobHistoryDaoImpl
                .findById(jobHistory.getId()))
                .thenReturn(Optional.of(jobHistory));

        JobHistoryItemDto result = jobHistoryServiceImpl.getById(jobHistory.getId());

        assertEquals(jobHistory, result);

        verify(jobHistoryDaoImpl).findById(jobHistory.getId());
    }

    @Test
    void pin() {
        JobHistory jobHistoryToSeve = new JobHistory(1, 1, 1, 1, true);
        JobHistory sevedJobHistory = new JobHistory(1, 1, 1, 1, true);

        when(jobHistoryDaoImpl.pin(any(JobHistory.class))).thenReturn(sevedJobHistory);

        JobHistoryCreateResponse result = jobHistoryServiceImpl.pin(jobHistoryToSeve);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);

        verify(jobHistoryDaoImpl).pin(jobHistoryToSeve);
        verify(jobHistoryDaoImpl, times(1)).pin(jobHistoryToSeve);
    }

    @Test
    void unPin() {
        JobHistory jobHistoryUnPin = new JobHistory(1, 1, 1, 1, true);
        JobHistory unPinjobHistory = new JobHistory(1, 1, 1, 1, true);

        when(jobHistoryDaoImpl.unPin(any(JobHistory.class))).thenReturn(unPinjobHistory);

        JobHistoryCreateResponse result = jobHistoryServiceImpl.unPin(jobHistoryUnPin);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);

        verify(jobHistoryDaoImpl).unPin(jobHistoryUnPin);
        verify(jobHistoryDaoImpl, times(1)).unPin(jobHistoryUnPin);
    }

    @Test
    void deleteById() {
        JobHistoryItemDto jobHistory = new JobHistoryItemDto(1,
                new Vacancy(1, "a", "a", 1, true, LocalDate.now()),
                new HrEmployee(1, "s", "s", "s"),
                new Employee(1, "d", "d", "d", "d", 1, LocalDate.now()),
                true);

        when(jobHistoryDaoImpl.findById(jobHistory.getId())).thenReturn(Optional.of(jobHistory));

        jobHistoryServiceImpl.deleteById(jobHistory.getId());

        verify(jobHistoryDaoImpl, times(1)).deleteById(jobHistory.getId());

    }
}
