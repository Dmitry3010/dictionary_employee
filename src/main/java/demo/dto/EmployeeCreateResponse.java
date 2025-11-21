package demo.dto;

public class EmployeeCreateResponse {

    private Integer id;

    public EmployeeCreateResponse(Integer id) {
        this.id = id;
    }

    public EmployeeCreateResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static EmployeeCreateResponse of(Integer id) {
        return new EmployeeCreateResponse(id);
    }
}
