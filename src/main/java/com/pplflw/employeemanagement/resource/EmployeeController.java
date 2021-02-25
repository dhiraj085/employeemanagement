/**
 * @author : Dhiraj
 */
package com.pplflw.employeemanagement.resource;

import com.pplflw.employeemanagement.data.Employee;
import com.pplflw.employeemanagement.data.EmployeeStateEnum;
import com.pplflw.employeemanagement.exception.EmployeeNotFoundException;
import com.pplflw.employeemanagement.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    @Operation(summary = "Create new employee")
    @ApiResponse(responseCode = "200", description = "Created the employee", content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Employee.class))})
    @ApiResponse(responseCode = "500", description = "Internal Server error", content = {@Content(mediaType = "application/json")})
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employee.setEmployeeStateEnum(EmployeeStateEnum.ADDED);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
    }

    @PatchMapping("/employees/{id}")
    @Operation(summary = "Update the status of an employee")
    @ApiResponse(responseCode = "200", description = "Updated the status of employee", content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Employee.class))})
    @ApiResponse(responseCode = "404", description = "Employee not found", content = {@Content(mediaType = "application/json")})
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestParam String status) {
        Employee savedEmployee = employeeService.findEmployee(id);
        if (savedEmployee != null) {
            savedEmployee.setEmployeeStateEnum(EmployeeStateEnum.valueOf(status));
            Employee updatedEmployee = employeeService.saveEmployee(savedEmployee);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            throw new EmployeeNotFoundException();
        }


    }
}
