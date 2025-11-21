package demo.dao;



import demo.dto.JobHistoryCreateResponse;
import demo.dto.JobHistoryItemDto;
import demo.model.JobHistory;

import java.util.List;
import java.util.Optional;

public interface JobHistoryDao {

    List<JobHistoryItemDto> findAll();

    List<JobHistoryItemDto> findAllIsActive();

    Optional<JobHistoryItemDto> findById (Integer id);

    JobHistory pin (JobHistory jobHistory);

    JobHistory unPin (JobHistory jobHistory);

    boolean deleteById (Integer id);
}

// создать метод закрепления сотрудника за вакансией (передаем id сотрудника который закрепил и
// id которого мы закрепляем + id вакансии) дата создания генерится сама (на уровне БД)

// создать метод открепления сотрудника
// при вызове этих методов мы делаем запись в двух новых таблицах
// создать метод отобразить всех сотрудников с вакансией

//pin unpin
