package demo.dao.mapper;

import demo.dto.EmployeeItemDto;
import demo.model.City;
import demo.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper  {

    public static class EmployeeSimpleMapper implements RowMapper<EmployeeItemDto> {

        @Override
        public EmployeeItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmployeeItemDto employee = new EmployeeItemDto();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("first_name"));
            employee.setSurname(rs.getString("surname"));
            employee.setPatronymic(rs.getString("middle_name"));
            employee.setDescription(rs.getString("description"));
            City city = new City();
            city.setName(rs.getString("city_name"));
            city.setId(rs.getInt("city_id"));
            employee.setCity(city);
            employee.setDate(rs.getDate("start_date").toLocalDate());
            return employee;
        }
    }
}
