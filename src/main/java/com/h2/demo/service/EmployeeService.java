package com.h2.demo.service;

import com.h2.demo.model.Employee;
import com.h2.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public String addEmployee(Employee employee) {
        String message;
        if(employeeRepository.add(employee)) message = "Employee added successfully";
        else message = "Insert failed";
        return message;
    }

    public String updateEmployee(Employee employee) {
        String message;
        if(employeeRepository.update(employee)) message = "Employee updated successfully";
        else message = "Update failed";
        return message;
    }

    public String deleteEmployee(int id) {
        String message;
        if(employeeRepository.delete(id)) message = "Employee deleted successfully";
        else message = "Delete failed";
        return message;
    }
}
