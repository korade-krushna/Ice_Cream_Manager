package com.icecream.dao;

import com.icecream.models.Employee;
import com.icecream.models.IceCream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IceCreamRepositary extends JpaRepository<IceCream, Integer> {

    @Query(value = "select u from Employee u where u.name =:name")
    IceCream getIceCreambyName(@Param("name") String name);
}
