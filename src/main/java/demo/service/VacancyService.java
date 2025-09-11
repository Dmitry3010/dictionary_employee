package demo.service;

import demo.model.Vacancy;

import java.util.List;

public interface VacancyService {

    List<Vacancy> getAll();

    Vacancy create(Vacancy vacancy);

    Vacancy update(Vacancy vacancy);

    boolean deleteById(int id);
}
