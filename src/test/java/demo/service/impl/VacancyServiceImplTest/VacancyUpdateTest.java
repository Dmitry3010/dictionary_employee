package demo.service.impl.VacancyServiceImplTest;

import demo.dao.impl.VacancyDaoImpl;
import demo.exception.NotFoundException;
import demo.model.Vacancy;
import demo.service.impl.VacancyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VacancyUpdateTest {

    @Mock
    private VacancyDaoImpl vacancyDaoImpl;

    @InjectMocks
    private VacancyServiceImpl vacancyServiceImpl;

    @Test
    void updateOutId() {
        int id = 123;
        Vacancy updateVacancy = new Vacancy(id, "qwe", "qwe",1,true, LocalDate.now());

        when(vacancyDaoImpl.findById(id))
                .thenThrow(new NotFoundException(String.format("Вакансии с таким ID: %s не нашлось в списке", id)));

        assertThrows(NotFoundException.class, () -> {
            vacancyServiceImpl.update(updateVacancy);
        });

        verify(vacancyDaoImpl, never()).update(any(Vacancy.class));
        verify(vacancyDaoImpl).findById(id);
    }
}
