package demo.dao.impl;

import demo.dao.VacancyDao;
import demo.enumVacancy.VacancyName;
import demo.model.Vacancy;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VacancyDaoImpl implements VacancyDao{

    private List<Vacancy> vacancyList = new ArrayList<>();
    private int idVacancy = 1;

    public VacancyDaoImpl(){
        create(new Vacancy(0, "Разработчик", "текст", "Москва", true, LocalDate.of(2025,03,21)));
        create(new Vacancy(0, "Тестировщик", "текст", "Москва", true, LocalDate.of(2025,05,10)));
    }

    @Override
    public List<Vacancy> findAll() {
        return vacancyList;
    }

    @Override
    public Optional<Vacancy> findById(Integer id) {
        return vacancyList.stream()
                .filter(vacancy -> vacancy.getId() == id)
                .findFirst();
    }

    @Override
    public Vacancy create(Vacancy vacancy) {
        vacancy.setId(idVacancy++);
        vacancy.setName(vacancy.getName());
        vacancy.setAccessibility(true);
        vacancy.setDate(LocalDate.now());
        vacancyList.add(vacancy);
        return vacancy;
    }

    @Override
    public Vacancy update(Vacancy vacancy) {
        int vacancyId = vacancy.getId();
        vacancyList.stream()
                .filter(vacancyUp -> vacancyUp.getId() == vacancyId)
                .forEach(vacancyUp -> {
                    vacancyUp.setName(vacancy.getName());
                    vacancyUp.setDescription(vacancy.getDescription());
                    vacancyUp.setCityName(vacancy.getCityName());
                    vacancyUp.setAccessibility(vacancy.isAccessibility());
                    vacancyUp.setDate(LocalDate.now());
                });
        return vacancy;
    }

    @Override
    public boolean deleteById(Integer id) {
        return findAll().removeIf(vacancy -> vacancy.getId() == id);
    }
}
