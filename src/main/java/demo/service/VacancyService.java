package demo.service;

import demo.model.Employee;
import demo.model.Vacancy;

import java.util.List;

public interface VacancyService {

    List<Vacancy> getAll();

    Vacancy getById (Integer id);

    Vacancy create(Vacancy vacancy);

    Vacancy update(Vacancy vacancy);

    boolean deleteById(Integer id);
}
