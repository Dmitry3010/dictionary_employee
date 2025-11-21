package demo.service.impl;

import demo.dao.JobHistoryDao;
import demo.dto.JobHistoryCreateResponse;
import demo.dto.JobHistoryItemDto;
import demo.exception.NotFoundException;
import demo.model.JobHistory;
import demo.service.JobHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobHistoryServiceImpl implements JobHistoryService {

    JobHistoryDao jobHistoryDao;

    public JobHistoryServiceImpl(JobHistoryDao jobHistoryDao) {
        this.jobHistoryDao = jobHistoryDao;
    }

    @Override
    public List<JobHistoryItemDto> getAll() {
        return jobHistoryDao.findAll();
    }

    @Override
    public List<JobHistoryItemDto> getAllIsActive() {
        return jobHistoryDao.findAllIsActive();
    }

    @Override
    public JobHistoryItemDto getById(Integer id) {
        return jobHistoryDao.findById(id)
                .orElseThrow(()-> new NotFoundException(String.format("Сотрудника таким ID: %s не нашлось в списке", id)));
    }

    @Override
    public JobHistoryCreateResponse pin(JobHistory jobHistory) {
        JobHistory createdJobHistory = jobHistoryDao.pin(jobHistory);
        return JobHistoryCreateResponse.of(createdJobHistory.getId());
    }

    @Override
    public JobHistoryCreateResponse unPin(JobHistory jobHistory) {
        JobHistory createdJobHistory = jobHistoryDao.unPin(jobHistory);
        return JobHistoryCreateResponse.of(createdJobHistory.getId());
    }

    @Override
    public boolean deleteById(Integer id) {
        jobHistoryDao.findById(id)
                .orElseThrow(()-> new NotFoundException(String.format("Сотрудника таким ID: %s не нашлось в списке", id)));
        return jobHistoryDao.deleteById(id);
    }
}
