package demo.service;

import demo.dto.EmployeeCreateResponse;
import demo.dto.JobHistoryCreateResponse;
import demo.dto.JobHistoryItemDto;
import demo.model.Employee;
import demo.model.JobHistory;

import java.util.List;
import java.util.Optional;

public interface JobHistoryService {

    List<JobHistoryItemDto> getAll();

    List<JobHistoryItemDto> getAllIsActive();

    JobHistoryItemDto getById (Integer id);

    JobHistoryCreateResponse pin (JobHistory jobHistory);

    JobHistoryCreateResponse unPin (JobHistory jobHistory);

    boolean deleteById (Integer id);
}
