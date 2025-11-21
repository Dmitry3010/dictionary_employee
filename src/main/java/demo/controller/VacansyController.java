package demo.controller;

import demo.dto.VacancyCreateResponse;
import demo.dto.VacancyItemDto;
import demo.model.Vacancy;
import demo.service.VacancyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacancy")
public class VacansyController {

    private VacancyService vacancyService;

    public VacansyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping("/all")
    public List<VacancyItemDto> getAll() {
        return vacancyService.getAll();
    }

    @GetMapping("/{id}")
    public VacancyItemDto getById(@PathVariable Integer id) {
        return vacancyService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public VacancyCreateResponse create(@RequestBody Vacancy vacancy) {
        return vacancyService.create(vacancy);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Vacancy vacancy) {
        vacancyService.update(vacancy);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        return vacancyService.deleteById(id);
    }
}
