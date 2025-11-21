package demo.dao.impl;

import demo.dao.JobHistoryDao;
import demo.dao.mapper.JobHistoryMapper;
import demo.dto.JobHistoryCreateResponse;
import demo.dto.JobHistoryItemDto;
import demo.model.JobHistory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class JobHistoryDaoImpl implements JobHistoryDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JobHistoryDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<JobHistoryItemDto> findAll() {
        String sql = "select jh.id, e.id as employee_id, he.id as hr_employee_id, v.id as vacancy_id, jh.created_at from job_hunt.job_history jh \n" +
                          "left join job_hunt.employee e on e.id = jh.employee_id \n" +
                          "left join job_hunt.hr_employee he on he.id = jh.hr_id \n" +
                          "left join job_hunt.vacancy v on v.id = jh.vacancy_id;";
                return namedParameterJdbcTemplate.query(sql, new JobHistoryMapper());
    }

    @Override
    public List<JobHistoryItemDto> findAllIsActive() {
        String sql = "select jh.id, e.id as employee_id, he.id as hr_employee_id, v.id as vacancy_id, jh.created_at from job_hunt.job_history jh \n" +
                "left join job_hunt.employee e on e.id = jh.employee_id \n" +
                "left join job_hunt.hr_employee he on he.id = jh.hr_id \n" +
                "left join job_hunt.vacancy v on v.id = jh.vacancy_id" +
                "where v.accessibility = 'true';";
        return namedParameterJdbcTemplate.query(sql, new JobHistoryMapper());
    }

    @Override
    public Optional<JobHistoryItemDto> findById(Integer id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
                param.addValue("id", id);
        String sql = "select jh.id, e.id as employee_id, he.id as hr_employee_id, v.id as vacancy_id, jh.created_at from job_hunt.job_history jh \n" +
                        "left join job_hunt.employee e on e.id = jh.employee_id \n" +
                        "left join job_hunt.hr_employee he on he.id = jh.hr_id \n" +
                        "left join job_hunt.vacancy v on v.id = jh.vacancy_id" +
                        "where jh.id = :id;";
                return namedParameterJdbcTemplate.query(sql, param, new JobHistoryMapper())
                        .stream().findFirst();
    }

    @Override
    @Transactional
    public JobHistory pin(JobHistory jobHistory) {
        MapSqlParameterSource paramHistory = new MapSqlParameterSource();
        paramHistory.addValue("vacancy_id", jobHistory.getVacancyId());
        paramHistory.addValue("hr_id", jobHistory.getHrId());
        paramHistory.addValue("employee_id", jobHistory.getEmployeeId());
        String sql = "INSERT INTO job_hunt.job_history (vacancy_id, hr_id, employee_id)\n" +
                     "VALUES(:vacancy_id, :hr_id, :employee_id)\n" +
                     "RETURNING id;";
        Integer generatedId = namedParameterJdbcTemplate.queryForObject(sql, paramHistory, Integer.class);
        jobHistory.setId(generatedId);

        MapSqlParameterSource paramJob = new MapSqlParameterSource();
        paramJob.addValue("employee_id", jobHistory.getEmployeeId());
        paramJob.addValue("vacancy_id", jobHistory.getVacancyId());
        String sqlJob = "INSERT INTO job_hunt.job (employee_id, vacancy_id)\n" +
                        " VALUES (:employee_id, :vacancy_id)\n" +
                        "ON CONFLICT ON CONSTRAINT uq_job_employee_vacancy DO NOTHING;";
        namedParameterJdbcTemplate.update(sqlJob, paramJob);

        MapSqlParameterSource paramVacancy = new MapSqlParameterSource();
        paramVacancy.addValue("vacancy_id", jobHistory.getVacancyId());
        String sqlVacancy = "update job_hunt.vacancy v set accessibility = 'false'\n" +
                            "where v.id = :vacancy_id;";
        namedParameterJdbcTemplate.update(sqlVacancy, paramVacancy);

        return jobHistory;
    }

    @Override
    public JobHistory unPin(JobHistory jobHistory) {
        MapSqlParameterSource paramHistory = new MapSqlParameterSource();
        paramHistory.addValue("vacancy_id", jobHistory.getVacancyId());
        paramHistory.addValue("hr_id", jobHistory.getHrId());
        paramHistory.addValue("employee_id", jobHistory.getEmployeeId());
        String sql = "INSERT INTO job_hunt.job_history (vacancy_id, hr_id, employee_id)\n" +
                "VALUES(:vacancy_id, :hr_id, :employee_id)\n" +
                "RETURNING id;";
        Integer generatedId = namedParameterJdbcTemplate.queryForObject(sql, paramHistory, Integer.class);
        jobHistory.setId(generatedId);

        MapSqlParameterSource paramJob = new MapSqlParameterSource();
        paramJob.addValue("employee_id", jobHistory.getEmployeeId());
        paramJob.addValue("vacancy_id", jobHistory.getVacancyId());
        String sqlJob = "INSERT INTO job_hunt.job (employee_id, vacancy_id)\n" +
                " VALUES (:employee_id, :vacancy_id)\n" +
                "ON CONFLICT ON CONSTRAINT uq_job_employee_vacancy DO NOTHING;";
        namedParameterJdbcTemplate.update(sqlJob, paramJob);

        MapSqlParameterSource paramVacancy = new MapSqlParameterSource();
        paramVacancy.addValue("vacancy_id", jobHistory.getVacancyId());
        String sqlVacancy = "update job_hunt.vacancy v set accessibility = 'true'\n" +
                "where v.id = :vacancy_id;";
        namedParameterJdbcTemplate.update(sqlVacancy, paramVacancy);

        return jobHistory;
    }

    @Override
    public boolean deleteById(Integer id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        String sql = "delete from job_hunt.job_history jh where jh.id = :id;";
        int rows = namedParameterJdbcTemplate.update(sql, param);
        return rows == 1;
    }
}
