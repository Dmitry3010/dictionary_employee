package demo.dao.impl;

import demo.dao.HrEmployeeDao;
import demo.dao.mapper.EmployeeMapper;
import demo.dao.mapper.HrEmployeeMapper;
import demo.dto.HrEmployeeItemDto;
import demo.exception.NotFoundException;
import demo.model.HrEmployee;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class HrEmployeeDaoImpl implements HrEmployeeDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public HrEmployeeDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<HrEmployeeItemDto> findAll() {
        String sql = "select * from job_hunt.hr_employee;";
        return namedParameterJdbcTemplate.query(sql, new HrEmployeeMapper());
    }

    @Override
    public Optional<HrEmployeeItemDto> findById(Integer id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        String sql = "select h.id, h.first_name, h.middle_name, h.last_name\n" +
                "from job_hunt.hr_employee h\n" +
                "where h.id = :id;";
        return namedParameterJdbcTemplate.query(sql, param, new HrEmployeeMapper())
                .stream().findFirst();
    }

    @Override
    public HrEmployee create(HrEmployee hrEmployee) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("first_name", hrEmployee.getFirstName());
        param.addValue("middle_name", hrEmployee.getMiddleName());
        param.addValue("last_name", hrEmployee.getLastName());

        String sql = "INSERT INTO job_hunt.hr_employee (first_name, middle_name, last_name)\n" +
                "                VALUES(:first_name, :middle_name, :last_name)\n" +
                "                RETURNING id;";

        Integer generatedId = namedParameterJdbcTemplate.queryForObject(sql, param, Integer.class);
        hrEmployee.setId(generatedId);
        return hrEmployee;
    }

    @Override
    public void update(HrEmployee hrEmployee) {
        findById(hrEmployee.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Not Found %s", hrEmployee.getId())));

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", hrEmployee.getId());
        param.addValue("first_name", hrEmployee.getFirstName());
        param.addValue("middle_name", hrEmployee.getMiddleName());
        param.addValue("last_name", hrEmployee.getLastName());

        String sql = "update job_hunt.hr_employee h\n" +
                "                set first_name = :firstName,\n" +
                "                middle_name = :middleName,\n" +
                "                last_name = :lastName\n" +
                "                where h.id = :id;";

        namedParameterJdbcTemplate.update(sql, param);
    }

    @Override
    public boolean deleteById(Integer id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        String sql = "delete from job_hunt.hr_employee h where h.id = :id;";
        int rows = namedParameterJdbcTemplate.update(sql, param);
        return rows == 1;
    }
}
