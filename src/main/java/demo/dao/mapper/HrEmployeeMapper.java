package demo.dao.mapper;

import demo.dto.EmployeeItemDto;
import demo.dto.HrEmployeeItemDto;
import demo.model.City;
import demo.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HrEmployeeMapper implements RowMapper<HrEmployeeItemDto> {

    @Override
    public HrEmployeeItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        HrEmployeeItemDto hrEmployee = new HrEmployeeItemDto();
        hrEmployee.setId(rs.getInt("id"));
        hrEmployee.setFirstName(rs.getString("first_name"));
        hrEmployee.setMiddleName(rs.getString("middle_name"));
        hrEmployee.setLastName(rs.getString("last_name"));
        return hrEmployee;
    }
}
