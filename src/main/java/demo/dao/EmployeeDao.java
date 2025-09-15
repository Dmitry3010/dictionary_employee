package demo.dao;

import demo.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    List<Employee> findAll();

    Optional<Employee> findById (int id);

    Employee create (Employee employee);

    Employee update (Employee employee);

    boolean deleteById (int id);
}

//1.  Получить список кандидатов:
//ФИО, описание, город(название города).
//Должно быть постраничное отображение( каждая страница по 10) возможность сортировки по фамилии
// ( я выбрал по каком критерию хочу сортировать, по фамилии имени отчеству или по фамилии или по имени или по отчеству.)
//
//2. Создание кандидата:
//Имя, фамилия, отчество, описание, город, дата создание.
//Дата создания определяется автоматически.
//
//3. Обновить кандидата:
//Имя, фамилия, отчество, город, дата обновления.
//
//4. Удалить кандидата.
