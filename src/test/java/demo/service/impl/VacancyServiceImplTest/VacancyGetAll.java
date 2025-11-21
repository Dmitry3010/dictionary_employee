package demo.service.impl.VacancyServiceImplTest;

import demo.dao.impl.VacancyDaoImpl;
import demo.dto.VacancyItemDto;
import demo.model.Vacancy;
import demo.service.impl.VacancyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VacancyGetAll {

    @Mock
    private VacancyDaoImpl VacancyDaoImpl;

    @InjectMocks
    private VacancyServiceImpl vacancyServiceImpl;

    @Test
    void getAllIsEmpty() {
        List<VacancyItemDto> vacancyList = new ArrayList<>();

        when(VacancyDaoImpl
                .findAll())
                .thenReturn(vacancyList);

        List<VacancyItemDto> result = vacancyServiceImpl.getAll();

        assertThat(result).isEmpty();

        verify(VacancyDaoImpl).findAll();
    }

}
