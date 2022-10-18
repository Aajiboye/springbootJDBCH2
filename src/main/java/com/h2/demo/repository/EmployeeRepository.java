package com.h2.demo.repository;

import com.h2.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
@Repository
public class EmployeeRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String GET_ALL = "SELECT * FROM employee";
    private final String INSERT = "INSERT INTO employee (first_name, last_name, address) values (?, ?, ?)";

    private final String UPDATE = "UPDATE employee set first_name = ?, last_name = ?, address = ? where id = ?";

    private final String DELETE = "DELETE from employee where id = ?";

    private RowMapper<Employee> rowMapper = (ResultSet rs, int rowNum) -> {
        Employee emp = new Employee();
        emp.setId(rs.getInt(1));
        emp.setFirstName(rs.getString(2));
        emp.setLastName(rs.getString(3));
        emp.setAddress(rs.getString(4));
        emp.setJoiningDate(rs.getString(5));
        return emp;
    };
    public List<Employee> findAll() {
        return jdbcTemplate.query(GET_ALL, rowMapper);
    }

    public Boolean add(Employee employee) {
        int query = jdbcTemplate.update(INSERT, employee.getFirstName(), employee.getLastName(), employee.getAddress());
        if(query > 0) return true;
        else return false;
    }

    public boolean update(Employee employee) {
        int query = jdbcTemplate.update(UPDATE, employee.getFirstName(), employee.getLastName(), employee.getAddress(), employee.getId());
        if(query > 0) return true;
        else return false;
    }

    public boolean delete(int id) {
        int query = jdbcTemplate.update(DELETE, id);
        if(query > 0) return true;
        else return false;
    }
}
