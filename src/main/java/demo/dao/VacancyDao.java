package demo.dao;

import demo.model.Employee;
import demo.model.Vacancy;

import java.util.List;
import java.util.Optional;

public interface VacancyDao {

    List<Vacancy> findAll();

    Optional<Vacancy> findById (int id);

    Vacancy create(Vacancy vacancy);

    Vacancy update(Vacancy vacancy);

    boolean deleteById(int id);
}

//1. Получить список вакансий
//Название, описание, город.
//Должно быть постраничное отображение (каждая страница по 10) возможность сортировки по названию города.
//
//2. Создание вакансии:
// Название, описание, город, видимость(флаг- доступна вакансия или нет), дата создания( автоматически определяется)
//
//3. Обновить вакансию:
//Название, описание, город, видимость, дата обновления.
//
//7. Удалить вакансию
