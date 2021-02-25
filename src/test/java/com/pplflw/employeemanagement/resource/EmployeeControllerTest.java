package com.pplflw.employeemanagement.resource;


import com.pplflw.employeemanagement.data.Employee;
import com.pplflw.employeemanagement.exception.EmployeeNotFoundException;
import com.pplflw.employeemanagement.service.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {
    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Test
    public void testCreateEmployee() {
        Employee employee = getEmployee();
        Mockito.when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);
        ResponseEntity<Employee> responseEntity = employeeController.createEmployee(employee);
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testUpdateEmployeeHappyScenario() {
        String status = "ACTIVE";
        Employee employee = getEmployee();
        Mockito.when(employeeService.findEmployee(anyLong())).thenReturn(employee);
        Mockito.when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);
        ResponseEntity<Employee> responseEntity = employeeController.updateEmployee(1, status);
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
        Assert.assertEquals(status, responseEntity.getBody().getEmployeeStateEnum().name());
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void testUpdateEmployeeNotFound() {
        String status = "ACTIVE";
        Employee employee = getEmployee();
        Mockito.when(employeeService.findEmployee(anyLong())).thenReturn(null);
        ResponseEntity<Employee> responseEntity = employeeController.updateEmployee(1, status);
    }

    private Employee getEmployee() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setAge(30);
        return employee;
    }

}