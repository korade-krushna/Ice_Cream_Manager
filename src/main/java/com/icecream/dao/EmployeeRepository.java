package com.icecream.dao;

import com.icecream.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // query for getting employee using username
    @Query(value = "select u from Employee u where u.email =:email")
    Employee getEmployeebyUsername(@Param("email") String email);

}
