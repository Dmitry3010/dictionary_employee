package demo.service;

import demo.dto.VacancyCreateResponse;
import demo.dto.VacancyItemDto;
import demo.model.Employee;
import demo.model.Vacancy;

import java.util.List;

public interface VacancyService {

    List<VacancyItemDto> getAll();

    VacancyItemDto getById (Integer id);

    VacancyCreateResponse create(Vacancy vacancy);

    void update(Vacancy vacancy);

    boolean deleteById(Integer id);
}
