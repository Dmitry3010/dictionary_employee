package demo.service.impl;

import demo.dao.VacancyDao;
import demo.enumVacancy.VacancyName;
import demo.exception.NotFoundException;
import demo.exception.NotFoundExceptionDoubleVacancy;
import demo.exception.NotFoundExceptionOutEmployee;
import demo.exception.NotFoundExceptionOutVacancy;
import demo.model.Vacancy;
import demo.service.VacancyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacancyServiceImpl implements VacancyService {

    private VacancyDao vacancyDao;

    public VacancyServiceImpl(VacancyDao vacancyDao) {
        this.vacancyDao = vacancyDao;
    }

    @Override
    public List<Vacancy> getAll() {
        List<Vacancy> vacancyList = vacancyDao.findAll();
        if (vacancyList.isEmpty()){
            throw new NotFoundException();
        }
        return vacancyList;
    }

    @Override
    public Vacancy create(Vacancy vacancy) {
        String pullVacancyName = vacancy.getName();
        for (VacancyName vacancyEnum : VacancyName.values()){
            String currentVacansyName = vacancyEnum.getName();
            if (pullVacancyName.equals(currentVacansyName)){
                throw new NotFoundExceptionDoubleVacancy();
            }
        }
        return vacancyDao.create(vacancy);
    }

    @Override
    public Vacancy update(Vacancy vacancy) {
        int vacancyId = vacancy.getId();
        Optional<Vacancy> vacancyUpdate = getAll().stream()
                .filter(vacancyUp -> vacancyUp.getId() == vacancyId)
                .findFirst();
        if (!vacancyUpdate.isPresent()){
            throw new NotFoundExceptionOutVacancy();
        }
        return vacancyDao.update(vacancy);
    }

    @Override
    public boolean deleteById(int id) {
        return getAll().stream()
                .filter(vacancyDel -> vacancyDel.getId() == id)
                .findFirst()
                .map(vacancyDel -> vacancyDao.deleteById(id))
                .orElseThrow(() -> new NotFoundExceptionOutVacancy());
    }
}
