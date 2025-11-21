package demo.service.impl;

import demo.dao.VacancyDao;
import demo.dto.EmployeeCreateResponse;
import demo.dto.VacancyCreateResponse;
import demo.dto.VacancyItemDto;
import demo.exception.NotFoundException;
import demo.model.Employee;
import demo.model.Vacancy;
import demo.service.VacancyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyServiceImpl implements VacancyService {

    private VacancyDao vacancyDao;

    public VacancyServiceImpl(VacancyDao vacancyDao) {
        this.vacancyDao = vacancyDao;
    }

    @Override
    public List<VacancyItemDto> getAll() {
        return vacancyDao.findAll();
    }

    @Override
    public VacancyItemDto getById(Integer id) {
        return vacancyDao.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Вакансии с таким ID: %s не нашлось в списке", id)));
    }

    @Override
    public VacancyCreateResponse create(Vacancy vacancy) {
        Vacancy createdVacancy = vacancyDao.create(vacancy);
        return VacancyCreateResponse.of(createdVacancy.getId());
    }

    @Override
    public void update(Vacancy vacancy) {
        vacancyDao.findById(vacancy.getId())
                .orElseThrow(() -> new NotFoundException("Сотрудник не найден"));
        vacancyDao.update(vacancy);
    }

    @Override
    public boolean deleteById(Integer id) {
        VacancyItemDto vacancyDelete = getById(id);
        int vacancyDeleteId = vacancyDelete.getId();
        return vacancyDao.deleteById(vacancyDeleteId);
    }
}
