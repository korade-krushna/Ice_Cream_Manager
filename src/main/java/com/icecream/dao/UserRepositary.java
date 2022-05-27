package com.icecream.dao;

import com.icecream.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositary extends JpaRepository<Employee, Integer> {

}
