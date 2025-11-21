package demo.controller;


import demo.dto.JobHistoryCreateResponse;
import demo.dto.JobHistoryItemDto;
import demo.model.JobHistory;
import demo.service.JobHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job_history")
public class JobHistoryController {

    private JobHistoryService jobHistoryService;

    public JobHistoryController(JobHistoryService jobHistoryService) {
        this.jobHistoryService = jobHistoryService;
    }

    @GetMapping("/all")
    public List<JobHistoryItemDto> getAll() {
        return jobHistoryService.getAll();
    }

    @GetMapping("/all_active")
    public List<JobHistoryItemDto> findAllIsActive() {
        return List.of();
    }

    @GetMapping("/{id}")
    public JobHistoryItemDto getById(@PathVariable Integer id) {
        return jobHistoryService.getById(id);
    }

    @PostMapping("/pin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public JobHistoryCreateResponse pin(@RequestBody JobHistory jobHistory) {
        return jobHistoryService.pin(jobHistory);

    }

    @PostMapping("/unpin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public JobHistoryCreateResponse unPin(@RequestBody JobHistory jobHistory) {
        return jobHistoryService.unPin(jobHistory);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        return jobHistoryService.deleteById(id);
    }
}
