package demo.service.impl.VacancyServiceImplTest;

import demo.dao.impl.VacancyDaoImpl;
import demo.exception.DuplicateException;
import demo.model.Vacancy;
import demo.service.impl.VacancyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VacancyCreateTest {

    @Mock
    private VacancyDaoImpl vacancyDaoImpl;

    @InjectMocks
    private VacancyServiceImpl vacancyServiceImpl;

    @Test
    void createDuplicateVacancy() {
        int id = 1;
        Vacancy vacancy = new Vacancy("www", "www", 1, true);

        when(vacancyDaoImpl
                .create(any(Vacancy.class)))
                .thenThrow(new DuplicateException(String.format("Данная вакансия уже существует в системе ID: %s", id)));

        assertThrows(DuplicateException.class, () -> {
                        vacancyServiceImpl.create(vacancy);
                    });

        verify(vacancyDaoImpl, times(1)).create(vacancy);
    }
}
