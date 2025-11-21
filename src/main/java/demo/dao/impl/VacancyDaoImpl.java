package demo.dao.impl;

import demo.dao.VacancyDao;
import demo.dao.mapper.EmployeeMapper;
import demo.dao.mapper.VacancyMapper;
import demo.dto.VacancyItemDto;
import demo.exception.NotFoundException;
import demo.model.Employee;
import demo.model.Vacancy;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class VacancyDaoImpl implements VacancyDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public VacancyDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<VacancyItemDto> findAll() {
        String sql = "select v.id, v.name, v.description, v.accessibility ,c.id as city_id, c.name as city_name, v.start_date \n" +
                "from job_hunt.vacancy v\n" +
                "left join job_hunt.city c \n" +
                "on c.id = v.fk_city\n" +
                "where v.accessibility = 'true';";
        return namedParameterJdbcTemplate.query(sql, new VacancyMapper.VacancySimpleMapper());
    }

    @Override
    public Optional<VacancyItemDto> findById(Integer id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        String sql = "select v.id, v.name, description, v.accessibility, c.id as city_id, c.name as city_name, v.start_date from job_hunt.vacancy v\n" +
                "left join job_hunt.city c on c.id = v.fk_city where v.id = :id;";
        return namedParameterJdbcTemplate.query(sql, param,  new VacancyMapper.VacancySimpleMapper())
                .stream().findFirst();
    }

    @Override
    public Vacancy create(Vacancy vacancy) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name", vacancy.getName());
        param.addValue("description", vacancy.getDescription());
        param.addValue("start_date", LocalDate.now());
        param.addValue("fk_city", vacancy.getCityId());
        param.addValue("accessibility", true);

        String sql = "INSERT INTO job_hunt.vacancy (name, description, accessibility, start_date, fk_city)\n" +
                "VALUES (:name, :description,:accessibility, :start_date, :fk_city)" +
                "RETURNING id;\n";

        Integer generatedId = namedParameterJdbcTemplate.queryForObject(sql, param, Integer.class);
        vacancy.setId(generatedId);
        return vacancy;
    }

    @Override
    public void update(Vacancy vacancy) {
        findById(vacancy.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Not Found %s", vacancy.getId())));

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", vacancy.getId());
        param.addValue("name", vacancy.getName());
        param.addValue("fkCity", vacancy.getCityId());

        String sql = "update job_hunt.vacancy v set name = :name, fk_city = :fkCity\n" +
               "where v.id = :id";

        namedParameterJdbcTemplate.update(sql, param);
        }

    @Override
    public boolean deleteById(Integer id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        String sql = "delete from job_hunt.vacancy v where v.id = :id;";
        int rows = namedParameterJdbcTemplate.update(sql, param);
        return rows == 1;
    }
}
