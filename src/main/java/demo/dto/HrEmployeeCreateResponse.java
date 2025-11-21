package demo.dto;

public class HrEmployeeCreateResponse {
    private Integer id;

    public HrEmployeeCreateResponse(Integer id) {
        this.id = id;
    }

    public HrEmployeeCreateResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static HrEmployeeCreateResponse of(int id) {
        return new HrEmployeeCreateResponse(id);
    }
}
