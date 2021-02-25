package com.pplflw.employeemanagement.service;

import com.pplflw.employeemanagement.data.Employee;
import com.pplflw.employeemanagement.data.EmployeeStateEnum;
import com.pplflw.employeemanagement.model.EmployeeEntity;
import com.pplflw.employeemanagement.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void testSaveEmployee() {
        EmployeeEntity employeeEntity = getEmployeeEntity();
        Mockito.when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(employeeEntity);
        Employee employee = new Employee();
        employee.setAge(30);
        employee.setEmployeeStateEnum(EmployeeStateEnum.ADDED);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        Assert.assertEquals(savedEmployee.getEmployeeStateEnum().name(), "ADDED");

    }

    @Test
    public void testFindEmployee() {
        Optional<EmployeeEntity> optionalEmployeeEntity = Optional.of(getEmployeeEntity());
        Mockito.when(employeeRepository.findById(anyLong())).thenReturn(optionalEmployeeEntity);
        Employee employee = employeeService.findEmployee(1);
        Assert.assertEquals(employee.getEmployeeStateEnum().name(), "ADDED");
    }

    private EmployeeEntity getEmployeeEntity() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1L);
        employeeEntity.setAge(30);
        employeeEntity.setEmployeeStateEnum(com.pplflw.employeemanagement.model.EmployeeStateEnum.ADDED);
        return employeeEntity;
    }

}