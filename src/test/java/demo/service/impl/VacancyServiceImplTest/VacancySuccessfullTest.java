package demo.service.impl.VacancyServiceImplTest;

import demo.dao.impl.VacancyDaoImpl;
import demo.dto.VacancyCreateResponse;
import demo.dto.VacancyItemDto;
import demo.model.City;
import demo.model.Employee;
import demo.model.Vacancy;
import demo.service.impl.VacancyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VacancySuccessfullTest {

    @Mock
    private VacancyDaoImpl vacancyDaoImpl;

    @InjectMocks
    private VacancyServiceImpl vacancyServiceImpl;

    @Test
    void getAll() {
        VacancyItemDto vacancy = new VacancyItemDto(123, "qwe", "qwe",new City(1, "Москва"),true, LocalDate.now());

        List<VacancyItemDto> vacancyList = Arrays.asList(vacancy);

        when(vacancyDaoImpl
                .findAll())
                .thenReturn(vacancyList);

        List<VacancyItemDto> result = vacancyServiceImpl.getAll();

        assertEquals(1, result.size());
        assertEquals("qwe", result.get(0).getName());

        verify(vacancyDaoImpl).findAll();
    }

    @Test
    void getById() {
        int id = 123;
        VacancyItemDto vacancy = new VacancyItemDto(id, "qwe", "qwe", new City(1, "Москва"),true, LocalDate.now());
        VacancyItemDto vacancy1 = new VacancyItemDto(1111, "www", "www", new City(1, "Москва"),true, LocalDate.now());

        when(vacancyDaoImpl
                .findById(id))
                .thenReturn(Optional.of(vacancy));

        VacancyItemDto vncy = vacancyServiceImpl.getById(id);

        assertThat(vncy.getName()).isEqualTo("qwe");
        assertThat(vncy.getName()).isNotEqualTo("www");
        assertThat(vncy.getId()).isEqualTo(123);
        assertThat(vncy.isAccessibility()).isTrue();
    }

    @Test
    void create() {
        int id = 123;
        Vacancy vacancy = new Vacancy(id, "qwe", "qwe", 1,true, LocalDate.now());
        Vacancy vacancy1 = new Vacancy(id, "qwe", "qwe", 1,true, LocalDate.now());

        when(vacancyDaoImpl.create(any(Vacancy.class))).thenReturn(vacancy);

        VacancyCreateResponse createVacancy = vacancyServiceImpl.create(vacancy1);

        assertThat(createVacancy).isNotNull();
        assertThat(createVacancy.getId()).isEqualTo(123);

        verify(vacancyDaoImpl, times(1)).create(vacancy);
    }

    @Test
    void update() {
        int id = 123;
        VacancyItemDto vacancy = new VacancyItemDto(id, "qwe", "qwe", new City(1, "Москва"),true, LocalDate.now());
        Vacancy vacancy1 = new Vacancy(123, "eee", "eee", 1,false, LocalDate.now());

        when(vacancyDaoImpl.findById(id)).thenReturn(Optional.of(vacancy));
        doNothing().when(vacancyDaoImpl).update(any(Vacancy.class));

        vacancyServiceImpl.update(vacancy1);

        verify(vacancyDaoImpl).findById(id);

        ArgumentCaptor<Vacancy> captor = ArgumentCaptor.forClass(Vacancy.class);
        verify(vacancyDaoImpl).update(captor.capture());
        Vacancy capturedEmployee = captor.getValue();

        assertThat(capturedEmployee.getName()).isEqualTo("eee");
        assertThat(capturedEmployee.getDescription()).isEqualTo("eee");
        assertThat(capturedEmployee.getCityId()).isEqualTo(1);
    }

    @Test
    void deleteById() {
        VacancyItemDto vacancy1 = new VacancyItemDto(123, "eee", "eee", new City(1, "Москва"),false, LocalDate.now());

        when(vacancyDaoImpl.findById(vacancy1.getId())).thenReturn(Optional.of(vacancy1));

        vacancyServiceImpl.deleteById(vacancy1.getId());

        verify(vacancyDaoImpl, times(1)).deleteById(vacancy1.getId());
    }
}