package demo.dto;

public class JobHistoryCreateResponse {
    private Integer id;

    public JobHistoryCreateResponse(Integer id) {
        this.id = id;
    }

    public JobHistoryCreateResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static JobHistoryCreateResponse of(Integer id) {
        return new JobHistoryCreateResponse(id);
    }
}
