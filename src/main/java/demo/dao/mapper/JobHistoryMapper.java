package demo.dao.mapper;

import demo.dto.JobHistoryItemDto;
import demo.model.City;
import demo.model.Employee;
import demo.model.HrEmployee;
import demo.model.Vacancy;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JobHistoryMapper implements RowMapper<JobHistoryItemDto> {

    @Override
    public JobHistoryItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        JobHistoryItemDto jobHistory = new JobHistoryItemDto();
                jobHistory.setId(rs.getInt("id"));
                jobHistory.setActive(rs.getBoolean("active"));
                jobHistory.setStartDate(rs.getDate("start_date"));

                Vacancy vacancy = new Vacancy();
                vacancy.setId(rs.getInt("vacancy_id"));
                vacancy.setName(rs.getString("vacancy_name"));
                jobHistory.setVacancy(vacancy);

                HrEmployee hrEmployee = new HrEmployee();
                hrEmployee.setId(rs.getInt("hr_id"));
                hrEmployee.setFirstName(rs.getString("hr_first_name"));
                hrEmployee.setMiddleName(rs.getString("hr_middle_name"));
                hrEmployee.setLastName(rs.getString("hr_last_name"));
                jobHistory.setHrEmployee(hrEmployee);

                Employee employee = new Employee();
                employee.setId(rs.getInt("employee_id"));
                employee.setSurname(rs.getString("emp_first_name"));
                employee.setName(rs.getString("emp_middle_name"));
                employee.setPatronymic(rs.getString("emp_last_name"));
                employee.setDescription(rs.getString("emp_description"));
                employee.setDate(rs.getDate("emp_start_date").toLocalDate());

                City city = new City();
                city.setName(rs.getString("city_name"));
                city.setId(rs.getInt("city_id"));
                //employee.setCity(city);

                jobHistory.setEmployee(employee);
                return jobHistory;
            }
}
