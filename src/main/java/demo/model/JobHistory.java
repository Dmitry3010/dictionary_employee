package demo.model;

import demo.dto.HrEmployeeItemDto;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class JobHistory {

    private int id;
    private int vacancyId;
    private int hrId;
    private int employeeId;
    private boolean active;
    private Date startDate;

    public JobHistory(int id, Date startDate, boolean active, int employeeId, int hrId, int vacancyId) {
        this.id = id;
        this.startDate = startDate;
        this.active = active;
        this.employeeId = employeeId;
        this.hrId = hrId;
        this.vacancyId = vacancyId;
    }

    public JobHistory(int id, int vacancyId, int hrId, int employeeId, boolean active) {
        this.id = id;
        this.vacancyId = vacancyId;
        this.hrId = hrId;
        this.employeeId = employeeId;
        this.active = active;
    }

    public JobHistory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }

    public int getHrId() {
        return hrId;
    }

    public void setHrId(int hrId) {
        this.hrId = hrId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JobHistory that = (JobHistory) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "JobHistory{" +
                "id=" + id +
                ", vacancyId=" + vacancyId +
                ", hrId=" + hrId +
                ", employeeId=" + employeeId +
                ", active=" + active +
                ", startDate=" + startDate +
                '}';
    }
}
