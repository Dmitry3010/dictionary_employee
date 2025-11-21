package demo.controller;

import demo.dto.EmployeeCreateResponse;
import demo.dto.EmployeeItemDto;
import demo.dto.HrEmployeeCreateResponse;
import demo.dto.HrEmployeeItemDto;
import demo.model.Employee;
import demo.model.HrEmployee;
import demo.service.EmployeeService;
import demo.service.HrEmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hr_employee")
public class HrEmployeeController {

    private HrEmployeeService hrEmployeeService;

    public HrEmployeeController(HrEmployeeService hrEmployeeService) {
        this.hrEmployeeService = hrEmployeeService;
    }

    @GetMapping("/all")
    public List<HrEmployeeItemDto> getAll() {
        return hrEmployeeService.getAll();
    }

    @GetMapping("/{id}")
    public HrEmployeeItemDto getById(@PathVariable Integer id) {
        return hrEmployeeService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public HrEmployeeCreateResponse create(@RequestBody HrEmployee hrEmployee) {
        return hrEmployeeService.create(hrEmployee);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody HrEmployee hrEmployee) {
        hrEmployeeService.update(hrEmployee);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        return hrEmployeeService.deleteById(id);
    }
}
