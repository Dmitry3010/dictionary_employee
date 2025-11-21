package demo.dao.impl;

import demo.dao.EmployeeDao;
import demo.dao.mapper.EmployeeMapper;
import demo.dto.EmployeeItemDto;
import demo.exception.NotFoundException;
import demo.model.Employee;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EmployeeDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<EmployeeItemDto> findAll() {
        String sql = "select e.id, surname, first_name, middle_name, description, name, e.start_date, c.id as city_id, c.name as city_name from job_hunt.employee e\n" +
                "left join job_hunt.city c on c.id = e.fk_city;";
        return namedParameterJdbcTemplate.query(sql, new EmployeeMapper.EmployeeSimpleMapper());
    }

    @Override
    public Optional<EmployeeItemDto> findById(Integer id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        String sql = "select e.id, surname, first_name, middle_name, description, name, e.start_date, c.id as city_id, c.name as city_name from job_hunt.employee e\n" +
                "left join job_hunt.city c on c.id = e.fk_city where e.id = :id;";
        return namedParameterJdbcTemplate.query(sql, param, new EmployeeMapper.EmployeeSimpleMapper())
                .stream().findFirst();
    }

    @Override
    public Employee create(Employee employee) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("surname", employee.getSurname());
        param.addValue("first_name", employee.getName());
        param.addValue("middle_name", employee.getPatronymic());
        param.addValue("description", employee.getDescription());
        param.addValue("start_date", LocalDate.now());
        param.addValue("fk_city", employee.getCityId());

        String sql = "INSERT INTO job_hunt.employee (surname, first_name, middle_name, description, start_date, fk_city) " +
                "VALUES(:surname, :first_name, :middle_name, :description, :start_date, :fk_city)" +
                "RETURNING id";

        Integer generatedId = namedParameterJdbcTemplate.queryForObject(sql, param, Integer.class);
        employee.setId(generatedId);
        return employee;
    }

    @Override
    public void update(Employee employee) {
        findById(employee.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Not Found %s", employee.getId())));

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", employee.getId());
        param.addValue("surname", employee.getSurname());
        param.addValue("firstName", employee.getName());
        param.addValue("middleName", employee.getPatronymic());
        param.addValue("fkCity", employee.getCityId());

        String sql = "update job_hunt.employee e\n" +
                "set surname = :surname,\n" +
                "    first_name = :firstName,\n" +
                "    middle_name = :middleName,\n" +
                "    fk_city = :fkCity\n" +
                "where e.id = :id;";

        namedParameterJdbcTemplate.update(sql, param);
    }

    @Override
    public boolean deleteById(Integer id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        String sql = "delete from job_hunt.employee e where e.id = :id;";
        int rows = namedParameterJdbcTemplate.update(sql, param);
        return rows == 1;
    }
}
