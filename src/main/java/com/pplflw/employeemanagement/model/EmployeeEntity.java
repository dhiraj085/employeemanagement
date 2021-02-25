/**
 * @author : Dhiraj
 */
package com.pplflw.employeemanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE")
@Getter
@Setter
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String address;
    private String contractInfo;

    @Enumerated(EnumType.STRING)
    private EmployeeStateEnum employeeStateEnum;

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", contractInfo='" + contractInfo + '\'' +
                ", employeeStateEnum=" + employeeStateEnum +
                '}';
    }
}
