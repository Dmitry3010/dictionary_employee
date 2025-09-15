package demo.controller;

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
    public List<Vacancy> getAll() {
        return vacancyService.getAll();
    }

    @GetMapping("/{id}")
    public Vacancy getById(@PathVariable int id) {
        return vacancyService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Vacancy create(@RequestBody Vacancy vacancy) {
        return vacancyService.create(vacancy);
    }

    @PutMapping("/update")
    public Vacancy update(@RequestBody Vacancy vacancy) {
        return vacancyService.update(vacancy);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteById(@PathVariable int id) {
        return vacancyService.deleteById(id);
    }
}
