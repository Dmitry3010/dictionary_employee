package demo.service.impl;

import demo.dao.VacancyDao;
import demo.exception.NotFoundException;
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
    public List<Vacancy> getAll() {
        return vacancyDao.findAll();
    }

    @Override
    public Vacancy getById(int id) {
        return vacancyDao.findById(id)
                .orElseThrow(() -> new NotFoundException());
    }

    @Override
    public Vacancy create(Vacancy vacancy) {
        return vacancyDao.create(vacancy);
    }

    @Override
    public Vacancy update(Vacancy vacancy) {
        int vacancyId = vacancy.getId();
        Vacancy vacancyUpdate = getById(vacancyId);
        return vacancyDao.update(vacancyUpdate);
    }

    @Override
    public boolean deleteById(int id) {
        Vacancy vacancyDelete = getById(id);
        int vacancyDeleteId = vacancyDelete.getId();
        return vacancyDao.deleteById(vacancyDeleteId);
    }
}
