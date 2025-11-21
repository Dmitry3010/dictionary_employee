package demo.dao.mapper;

import demo.dto.VacancyItemDto;
import demo.model.City;
import demo.model.Employee;
import demo.model.Vacancy;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VacancyMapper {

    public static class VacancySimpleMapper implements RowMapper<VacancyItemDto> {

        @Override
        public VacancyItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            VacancyItemDto vacancy = new VacancyItemDto();
            vacancy.setId(rs.getInt("id"));
            vacancy.setName(rs.getString("name"));
            vacancy.setDescription(rs.getString("description"));
            City city = new City();
            city.setName(rs.getString("city_name"));
            city.setId(rs.getInt("city_id"));
            vacancy.setCity(city);
            vacancy.setAccessibility(rs.getBoolean("accessibility"));
            vacancy.setDate(rs.getDate("start_date").toLocalDate());
            return vacancy;
        }
    }
}
